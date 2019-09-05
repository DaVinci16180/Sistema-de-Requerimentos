package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller {
	
	@Before
	static void verificar() {
		if (session.contains("usuario.matricula") == false) {
			Login.form();
		}
	}
	
	
	@Before(unless={
		"Application.index",
		"Application.pesquisa",
		"Requerimentos.form",
		"Requerimentos.listar",
		"Requerimentos.salvarJus",
		"Requerimentos.salvarRep",
		"Requerimentos.deletar",
		"Application.personalizar"
	})
	static void permissaoAluno() {
		if (session.get("usuario.tipo").equals("Aluno")) {
			//Application.index();
			renderText("erro");
		}
	}
	
	@Before(unless={
			"Application.index",
			"Application.pesquisa",
			"Requerimentos.listar",
			"Application.personalizar"
		})
		static void permissaoProfessor() {
			if (session.get("usuario.tipo").equals("Professor")) {
				Application.index();
			}
		}
	
	@Before(only={"Requerimentos.form"})
	static void permissaoSeac() {
		if (session.get("usuario.tipo").equals("adm")) {
			Application.index();
		}
	}
}
