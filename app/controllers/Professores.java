package controllers;

import java.util.List;

import models.Aluno;
import models.Professor;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Professores extends Controller{
	
//	public static void form() {
//		render();
//	}
	
//	public static void salvar(Professor professor, String senha) {
//		if (senha.equals("") == false) {
//			professor.senha = senha;
//		}
//		professor.sidebar = "app-sidebar sidebar-shadow";
//		professor.header = "app-header header-shadow";
//		professor.save();
//		flash.success("Dados do professor cadastrados com sucesso!");
//		listar();
//	}
	
	public static void listar() {
		List<Professor> professores = Professor.findAll();
		render(professores);
	}
	
	public static void deletar(Long id) {
		Professor professor = Professor.findById(id);
		professor.delete();
		flash.success("Professor removido com sucesso!");
		listar();
	}
	
//	public static void editar(Long id) {
//		Professor professor = Professor.findById(id);
//		render("Professores/form.html", professor);
//	}
	
	public static void buscar(String busca) {
		List<Professor> professores = Professor.find("nome like ?", "%" + busca + "%").fetch();
		render("Professores/listar.html", professores);
	}
}
