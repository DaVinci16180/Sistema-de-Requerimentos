package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

@With(Seguranca.class)
public class Application extends Controller {
	
    public static void index() {
    	Usuario user = Usuario.find("byMatricula", session.get("usuario.matricula")).first();
    	List<Requerimento> requerimentos = null;
    	if (session.get("usuario.tipo").equals("adm")) {
    		requerimentos = Requerimento.find("status = ?", "2").fetch();
    	}
        render(user, requerimentos);
    }
    
    public static void pesquisa(String pesquisa) {
    	List<Requerimento> requerimentos;
    	List<Aluno> alunos = null;
    	List<Professor> professores = null;
		if (session.get("usuario.tipo").equals("Aluno")) {
			requerimentos = Requerimento.find("professor.nome like ?1 and aluno.matricula like ?2", "%"+pesquisa+"%", session.get("usuario.matricula")).fetch();
			requerimentos.addAll(Requerimento.find("tipo like ?1", "%"+pesquisa+"%").fetch());
		} else if (session.get("usuario.tipo").equals("Professor")) {
			requerimentos = Requerimento.find("aluno.nome like ?1 and professor.matricula like ?2", "%"+pesquisa+"%", session.get("usuario.matricula")).fetch();
		} else {
			requerimentos = Requerimento.find("aluno.nome like ?1 or professor.nome like ?1", "%"+pesquisa+"%").fetch();
			alunos = Alunos.buscar(pesquisa);
			professores = Professores.buscar(pesquisa);
		}
		Collections.sort(requerimentos, new Comparator<Requerimento>() {
			@Override
			public int compare(Requerimento r1, Requerimento r2) {
				return r2.id.compareTo(r1.id);
			}
		});
		render(requerimentos, alunos, professores);
	}
    
    public static void personalizar(String mudaheader, String mudasidebar) {
		
		if (session.get("usuario.tipo").equals("adm") == false) {
			Usuario user = Usuario.find("byMatricula", session.get("usuario.matricula")).first();
			user.tema.header = mudaheader;
			user.tema.sidebar = mudasidebar;
			session.put("header", user.tema.header);
			session.put("sidebar", user.tema.sidebar);
			user.tema.save();
		} else {
			Administrador adm = Administrador.find("matricula = ?", session.get("usuario.matricula")).first();
			adm.header = mudaheader;
			adm.sidebar = mudasidebar;
			session.put("header", adm.header);
			session.put("sidebar", adm.sidebar);
			adm.save();
		}
		flash.success("Tema alterado com sucesso!");
		Application.index();
	}
    
    public static void perfilAluno(String matricula) {
    	Usuario user = Usuario.find("byMatricula", matricula).first();
    	render(user);
    }
    
    public static void perfilProf(String matricula) {
		List<Disciplina> disciplinasAdd = Disciplina.findAll();
		Professor professor = Professor.find("byMatricula", matricula).first();;
		List<Disciplina> disciplinas = professor.disciplinas;
		render(professor, disciplinas, disciplinasAdd);
    }
    
    public static void sobre() {
    	render();
    }
    
}
