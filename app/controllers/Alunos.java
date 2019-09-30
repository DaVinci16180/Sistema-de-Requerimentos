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
	
	public static List buscar(String busca) {
		List<Aluno> alunos = Aluno.find("nome like ?", "%" + busca + "%").fetch();
		return alunos;
	}
}


