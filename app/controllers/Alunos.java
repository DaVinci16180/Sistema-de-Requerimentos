package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Aluno;
import models.Professor;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Alunos extends Controller{
	
	public static void form() {
		render();
	}
	
	public static void salvar(Aluno aluno, String senha) {
		if (senha.equals("") == false) {
			aluno.senha = senha;
		}
		aluno.save();
		listar();
	}
	
	public static void listar() {
		List<Aluno> alunos = Aluno.findAll();
		render(alunos);
	}
	
	public static void deletar(Long id) {
		Aluno aluno = Aluno.findById(id);
		aluno.delete();
		listar();
	}
	
	public static void editar(Long id) {
		Aluno aluno = Aluno.findById(id);
		render("Alunos/form.html", aluno);
	}
	
	public static void buscar(String busca) {
		List<Aluno> alunos = Aluno.find("nome like ?", "%" + busca + "%").fetch();
		render("Alunos/listar.html", alunos);
	}
}
