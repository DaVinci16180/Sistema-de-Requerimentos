package controllers;

import java.util.List;

import models.Aluno;
import models.Professor;
import models.Requerimento;
import models.Usuario;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Requerimentos extends Controller{
	
	public static void form() {
		List<Professor> professores = Professor.findAll();
		Aluno aluno = Aluno.find("matricula = ?", session.get("usuario.matricula")).first();
		render(professores, aluno);
	}
	
	public static void salvarJus(Requerimento requerimento) {
		requerimento.tipo = "Justificativa de Faltas";
		requerimento.save();
		flash.success("Requerimento enviado com sucesso!");
		listar();
	}
	
	public static void salvarRep(Requerimento requerimento) {
		requerimento.tipo = "Reposição de Atividades";
		requerimento.save();
		flash.success("Requerimento enviado com sucesso!");
		listar();
	}
	
	public static void listar() {
		List<Requerimento> requerimentos;
		Usuario usuario = Usuario.find("matricula = ?", session.get("usuario.matricula")).first();
		if (session.get("usuario.tipo").equals("Aluno")) {
			Aluno aluno = (Aluno) usuario;
			requerimentos = aluno.requerimentos;
		} else if (session.get("usuario.tipo").equals("Professor")) {
			Professor professor = (Professor) usuario;
			requerimentos = professor.requerimentos;
		} else {
			requerimentos = Requerimento.findAll();
		}
		render(requerimentos);
	}
	
	public static void deletar(Long id) {
		Requerimento requerimento = Requerimento.findById(id);
		requerimento.delete();
		flash.success("Requerimento removido com sucesso!");
		listar();
	}
	
//	public static void editar(Long id) {
//		Requerimento requerimento = Requerimento.findById(id);
//		List<Professor> professores = Professor.findAll();
//		render("Requerimentos/form.html", requerimento, professores);
//	}
}
