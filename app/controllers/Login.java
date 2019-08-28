package controllers;

import models.Aluno;
import models.Usuario;
import play.libs.Crypto;
import play.mvc.Controller;

public class Login extends Controller{
	
	public static void form() {
		render();
	}
	
	public static void autentica(String email, String senha) {
		
		Usuario usuario = Usuario.find("email = ?1 and senha = ?2",
									email, Crypto.passwordHash(senha)).first();
		
		if (usuario == null) {
			form();
		} else {
			session.put("usuario.email", usuario.email);
			session.put("usuario.nome", usuario.nome);
			session.put("usuario.nivel", usuario.nivel);
			Application.index();
		}
	}
	
	public static void sair() {
		session.clear();
		form();
	}

}
