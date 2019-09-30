package controllers;

import java.util.List;

import models.Aluno;
import models.Disciplina;
import models.Professor;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Professores extends Controller{
	
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
	
	public static List buscar(String busca) {
		List<Professor> professores = Professor.find("nome like ?", "%" + busca + "%").fetch();
		return professores;
	}
	
	
	
	public static void adicionar(Professor professor, Long idDisciplina) {
		if (idDisciplina == null) {
			flash.error("Selecione uma Disciplina para Atribuir!");
			Application.perfilProf(professor.matricula);
		}
		Disciplina disciplina = Disciplina.findById(idDisciplina);
		if (professor.disciplinas.contains(disciplina)) {
			flash.error("Professor(a) '"+professor.nome+"' já cadastrado na disciplina!");
		} else {
			disciplina.professores.add(professor);
			disciplina.save();
			flash.success("Professor(a) adicionado(a) à disciplina '"+disciplina.nome+"' com sucesso!");
		}
		Application.perfilProf(professor.matricula);
	}
}
