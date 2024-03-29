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
		"Requerimentos.salvar",
		"Application.personalizar",
		"Application.perfilAluno",
		"Application.sobre",
		"ReqEspeciais.form",
		"ReqEspeciais.salvar",
		"ReqEspeciais.listar",
		"ReqEspeciais.deletar",
		"Requerimentos.reposicao",
		"Requerimentos.detalhes"
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
			"Application.personalizar",
			"Application.perfilProf",
			"Application.perfilAluno",
			"Application.sobre",
			"Disciplinas.deletarProfPerfil",
			"Professores.adicionar",
			"Requerimentos.detalhes",
			"Requerimentos.deferir",
			"Requerimentos.indeferir"
		})
		static void permissaoProfessor() {
			if (session.get("usuario.tipo").equals("Professor")) {
				//Application.index();
				renderText("erro");
			}
		}
	
	@Before(only={"Requerimentos.form"})
	static void permissaoSeac() {
		if (session.get("usuario.tipo").equals("adm")) {
			Application.index();
		}
	}
}
