package controllers;

import play.mvc.Controller;

public class Login extends Controller{
	
	public static void form() {
		render();
	}
	
	public static void autentica(String username, String password) {
		if(username.equals("seac") && password.equals("ifrn")){
			render("Application/index.html");
		} else {
			render("Login/negado.html");
		}
	}

}
