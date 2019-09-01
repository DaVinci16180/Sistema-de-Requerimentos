package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Aluno;
import models.Professor;
import models.Seac;
import models.Tema;
import models.Usuario;
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
		aluno.sidebar = "app-sidebar sidebar-shadow";
		aluno.header = "app-header header-shadow";
		aluno.save();
		flash.success("Dados do aluno cadastrados com sucesso!");
		listar();
	}
	
	public static void listar() {
		List<Aluno> alunos = Aluno.findAll();
		render(alunos);
	}
	
	public static void deletar(Long id) {
		Aluno aluno = Aluno.findById(id);
		aluno.delete();
		flash.success("Aluno removido com sucesso!");
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
	
	public static void personalizar(String mudaheader, String mudasidebar) {
		List<Usuario> usuario = Usuario.find("byEmail", session.get("usuario.email")).fetch(1);
		Usuario aluno = (Aluno) usuario.get(0);
		aluno.header = mudaheader;
		aluno.sidebar = mudasidebar;
		session.put("header", aluno.header);
		session.put("sidebar", aluno.sidebar);
		aluno.save();
		Application.index();
	}
}


