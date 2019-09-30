package controllers;

import java.util.List;

import javax.validation.Valid;


import models.Aluno;
import models.Disciplina;
import models.Professor;
import models.Requerimento;
import models.Usuario;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Requerimentos extends Controller{
	
	public static void disciplina() {
		
	}
	
	public static void form() {
		Aluno aluno = Aluno.find("matricula = ?", session.get("usuario.matricula")).first();
		List<Disciplina> disciplinas = Disciplina.find("curso like ?1 or curso like ?2", "Todos", aluno.curso).fetch();
		render(aluno, disciplinas);
	}
	
	
	public static void reposicao(Long idDisciplina) {
		if (idDisciplina == null) {
			flash.error("Selecione uma Disciplina!");
			form();
		} else {
			Aluno aluno = Aluno.find("matricula = ?", session.get("usuario.matricula")).first();
			Disciplina disciplina = Disciplina.findById(idDisciplina);
			List<Professor> professores = disciplina.professores;
			Cache.set("disciplinaId", idDisciplina);
			render(aluno, professores);
			
		}
	}
	
	public static void salvar(Requerimento requerimento, Long idProfessor) {
		// Testa se existem campos em branco no formulário de criação de requerimentos
		if (requerimento.data == null || idProfessor == null && requerimento.tipo.equals("Reposição de Atividades")) {
			flash.error("Preencha Todos os Dados para Continuar!");
			// Se houverem campos em branco em um requerimento do tipo "Reposição de Atividades"
			if (requerimento.tipo.equals("Reposição de Atividades")) { 
				Disciplina disciplina = Disciplina.findById(Cache.get("disciplinaId"));
				Cache.clear();
				reposicao(disciplina.id);
			// Se houverem campos em branco em um requerimento do tipo "Justificativa de Faltas"
			} else {
				form();
			}
		} else {
			if (requerimento.tipo.equals("Reposição de Atividades")) {
				Professor professor = Professor.findById(idProfessor);
				requerimento.professor = professor;
			}
			requerimento.save();
			flash.success("Requerimento enviado com sucesso!");
			listar();
		}
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
	
}
