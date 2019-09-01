package controllers;

import models.Aluno;
import models.Usuario;
import play.libs.Crypto;
import play.mvc.Controller;

public class Login extends Controller{

	public static void form(String email) {
		render(email);
	}
	
	public static void autentica(String email, String senha) {
		
		Usuario usuario = Usuario.find("email = ?1 and senha = ?2",
									email, Crypto.passwordHash(senha)).first();
		
		if (usuario == null) {
			flash.error("Por favor, entre com usuário e senha corretos.");
			form(email);
		} else {
			session.put("usuario.email", usuario.email);
			session.put("usuario.nome", usuario.nome);
			session.put("usuario.nivel", usuario.nivel);
			session.put("header", usuario.header);
			session.put("sidebar", usuario.sidebar);
			Application.index();
		}
	}
	
	public static void sair() {
		session.clear();
		form(null);
	}

}
