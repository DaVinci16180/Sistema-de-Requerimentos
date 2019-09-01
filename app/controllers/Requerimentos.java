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
		List<Aluno> alunos = Aluno.find("byEmail", session.get("usuario.email")).fetch(1);
		Aluno aluno = alunos.get(0);
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
		List<Usuario> usuario = Usuario.find("byEmail", session.get("usuario.email")).fetch(1);
		if (session.get("usuario.nivel").equals("1")) {
			Aluno aluno = (Aluno) usuario.get(0);
			requerimentos = aluno.requerimentos;
		} else if (session.get("usuario.nivel").equals("2")) {
			Professor professor = (Professor) usuario.get(0);
			requerimentos = professor.requerimentos;
		} else {
			requerimentos = Requerimento.findAll();
		}
		render(requerimentos);
	}
	
	public static void deletar(Long id) {
		Requerimento requerimento = Requerimento.findById(id);
		requerimento.delete();
		listar();
	}
	
//	public static void editar(Long id) {
//		Requerimento requerimento = Requerimento.findById(id);
//		List<Professor> professores = Professor.findAll();
//		render("Requerimentos/form.html", requerimento, professores);
//	}
}
