package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Aluno;
import models.Professor;
import models.Tema;
import models.Usuario;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Alunos extends Controller{
	
//	public static void form() {
//		render();
//	}
	
//	public static void salvar(Usuario aluno, String senha) {
//		if (senha.equals("") == false) {
//			aluno.senha = senha;
//		}
//		aluno.sidebar = "app-sidebar sidebar-shadow";
//		aluno.header = "app-header header-shadow";
//		aluno.save();
//		flash.success("Dados do aluno cadastrados com sucesso!");
//		listar();
//	}
	
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
	
//	public static void editar(Long id) {
//		Aluno aluno = Aluno.findById(id);
//		render("Alunos/form.html", aluno);
//	}
	
	
}


