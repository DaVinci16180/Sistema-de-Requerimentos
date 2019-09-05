package controllers;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import models.Aluno;
import models.DadosSUAP;
import models.Professor;
import models.Administrador;
import models.Usuario;
import play.libs.Crypto;
import play.libs.WS;
import play.mvc.Controller;

public class Login extends Controller{

	public static void form() {
		render();
	}
	
	
	
	public static void autenticarSuap(String matricula, String senha) {
		Administrador adm = Administrador.find("matricula = ? and senha = ?", matricula, senha).first();
		if (adm != null) {
			System.out.println("entrou no if");
			session.put("usuario.matricula", adm.matricula);
			session.put("usuario.nome", "Administrador");
			session.put("usuario.tipo", "adm");
			session.put("header", adm.header);
			session.put("sidebar", adm.sidebar);
			Application.index();

		} else {

			WS.HttpResponse resposta;

			String urlToken = "https://suap.ifrn.edu.br/api/v2/autenticacao/token/";
			String urlDados = "https://suap.ifrn.edu.br/api/v2/minhas-informacoes/meus-dados/";

			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("username", matricula);
			parametros.put("password", senha);

			resposta = WS.url(urlToken).params(parametros).post();

			if (resposta.success()) {

				String token = resposta.getJson().getAsJsonObject().get("token").getAsString();
				Map<String, String> header = new HashMap<String, String>();
				header.put("X-CSRFToken", token);
				header.put("Authorization", "JWT " + token);

				resposta = WS.url(urlDados).headers(header).get();

				DadosSUAP dadosSUAP = new Gson().fromJson(resposta.getString(), DadosSUAP.class);

				Usuario usuario = Usuario.find("matricula = ?", dadosSUAP.matricula).first();

				if (usuario == null) {
					if (dadosSUAP.tipo_vinculo.equals("Professor")) {
						usuario = new Professor();
					} else {
						usuario = new Aluno();
					}
					usuario.nome = dadosSUAP.nome_usual;
					usuario.matricula = dadosSUAP.matricula;
					usuario.tipoVinculo = dadosSUAP.tipo_vinculo;
					usuario.url_foto_75x100 = "http://suap.ifrn.edu.br" + dadosSUAP.url_foto_75x100;
					usuario.email = dadosSUAP.email;
					usuario.header = "app-header header-shadow";
					usuario.sidebar = "app-sidebar sidebar-shadow";
					usuario.save();
				}
				
				session.put("usuario.matricula", usuario.matricula);
				session.put("usuario.email", usuario.email);
				session.put("usuario.nome", usuario.nome);
				session.put("usuario.foto", usuario.url_foto_75x100);
				session.put("idUsuario", usuario.id);
				session.put("usuario.tipo", usuario.tipoVinculo);
				session.put("header", usuario.header);
				session.put("sidebar", usuario.sidebar);
				Application.index();// Página inicial
				return ;

			} else {
				flash.error("Por favor, entre com usuário e senha corretos.");
				form();// Redireciona para o form de login
			}
		}
	}
	
	
	
	
	
//	public static void autentica(String email, String senha) {
//		
//		Usuario usuario = Usuario.find("email = ?1 and senha = ?2",
//									email, Crypto.passwordHash(senha)).first();
//		
//		if (usuario == null) {
//			flash.error("Por favor, entre com usuário e senha corretos.");
//			form(email);
//		} else {
//			session.put("usuario.email", usuario.email);
//			session.put("usuario.nome", usuario.nome);
//			session.put("usuario.nivel", usuario.nivel);
//			session.put("header", usuario.header);
//			session.put("sidebar", usuario.sidebar);
//			Application.index();
//		}
//	}
	
	public static void sair() {
		session.clear();
		form();
	}

}
