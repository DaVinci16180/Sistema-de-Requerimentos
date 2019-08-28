package controllers;

import java.util.List;

import models.Aluno;
import models.Professor;
import models.Requerimento;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Requerimentos extends Controller{
	
	public static void form() {
		
		List<Professor> professores = Professor.findAll();
		List<Aluno> alunos = Aluno.findAll();
		render(professores, alunos);
	}
	
	public static void salvarJus(Requerimento requerimento) {
		requerimento.tipo = "Justificativa de Faltas";
		requerimento.save();
		listar();
	}
	
	public static void salvarRep(Requerimento requerimento) {
		requerimento.tipo = "Reposição de Atividades";
		requerimento.save();
		listar();
	}
	
	public static void listar() {
		List<Requerimento> requerimentos = Requerimento.findAll();
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
