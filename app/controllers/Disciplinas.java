package controllers;

import java.util.List;

import models.Disciplina;
import models.Professor;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Disciplinas extends Controller {
	
	public static void form() {
		List<Disciplina> disciplinas = listar();
		render(disciplinas);
	}
	
	public static void salvar(@Valid Disciplina disciplina) {
		if (validation.hasErrors()) {
			validation.keep();
			flash.error("Insira os Dados da Disciplina!");
			form();
		}
		disciplina.save();
		flash.success("Dados da disciplina '"+disciplina.nome+"' cadastrados com sucesso!");
		form();
	}
	
	public static void adicionar(Disciplina disciplina, Long idProfessor) {
		if (idProfessor == null) {
			flash.error("Selecione um Professor para Adicionar!");
			dados(disciplina.id);
		}
		Professor professor = Professor.findById(idProfessor);
		if (disciplina.professores.contains(professor)) {
			flash.error("Professor(a) '"+professor.nome+"' já cadastrado na disciplina!");
		} else {
			disciplina.professores.add(professor);
			disciplina.save();
			flash.success("Professor(a) '"+professor.nome+"' adicionado(a) à disciplina com sucesso!");
		}
		dados(disciplina.id);
	}
	
	static List listar() {
		List<Disciplina> disciplinas = Disciplina.findAll();
		return disciplinas;
	}
	
	public static void deletar(Long id) {
		Disciplina disciplina = Disciplina.findById(id);
		disciplina.delete();
		flash.success("Disciplina '"+disciplina.nome+"' removida com sucesso!");
		form();
	}
	
	public static void editar(Long id) {
		Disciplina disciplina = Disciplina.findById(id);
		List<Disciplina> disciplinas = listar();
		render("Disciplinas/form.html", disciplina, disciplinas);
	}
	
	public static void dados(Long id) {
		List<Professor> professores = Professor.findAll();
		Disciplina disciplina = Disciplina.findById(id);
		List<Professor> professoresDis = disciplina.professores;
		render(disciplina, professores, professoresDis);
	}
	
	public static void deletarProf(Long idProf, Long idDisciplina) {
		Disciplina disciplina = Disciplina.findById(idDisciplina);
		Professor professor = Professor.findById(idProf);
		disciplina.professores.remove(professor);
		disciplina.save();
		flash.success("Professor(a) '"+professor.nome+"' removido(a) da disciplina  com sucesso!");
		dados(idDisciplina);
	}
	
	public static void deletarProfPerfil(Long idProf, Long idDisciplina) {
		Disciplina disciplina = Disciplina.findById(idDisciplina);
		Professor professor = Professor.findById(idProf);
		disciplina.professores.remove(professor);
		disciplina.save();
		flash.success("Professor(a) '"+professor.nome+"' removido(a) da disciplina  com sucesso!");
		Application.perfilProf(professor.matricula);
	}
}
