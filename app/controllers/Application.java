package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

@With(Seguranca.class)

public class Application extends Controller {
	
    public static void index() {
    	String user = session.get("usuario.nome");
        render(user);
    }
    
    public static void pesquisa(String pesquisa) {
    	List<Requerimento> requerimentos = null;
    	List<Aluno> alunos = null;
    	List<Professor> professores = null;
		if (session.get("usuario.nivel").equals("1")) {
			requerimentos = Requerimento.find("professor.nome like ?1 and aluno.email like ?2", "%"+pesquisa+"%", session.get("usuario.email")).fetch();
		} else if (session.get("usuario.nivel").equals("2")) {
			requerimentos = Requerimento.find("aluno.nome like ?1 and professor.email like ?2", "%"+pesquisa+"%", session.get("usuario.email")).fetch();
		} else {
			requerimentos = Requerimento.find("aluno.nome like ?1", "%"+pesquisa+"%").fetch();
			requerimentos.addAll(Requerimento.find("professor.nome like ?1", "%"+pesquisa+"%").fetch());
			alunos = Aluno.find("nome like ?1 or email like ?1 or matricula like ?1", "%"+pesquisa+"%").fetch();
			professores = Professor.find("nome like ?1 or email like ?1 or matricula like ?1", "%"+pesquisa+"%").fetch();
		}
		render(requerimentos, alunos, professores);
	}
    
    public static void personalizar(String mudaheader, String mudasidebar) {
		List<Usuario> usuario = Usuario.find("byEmail", session.get("usuario.email")).fetch(1);
		Usuario user;
		if (session.get("usuario.nivel").equals("1")) {
			user = (Aluno) usuario.get(0);
		} else if (session.get("usuario.nivel").equals("2")) {
			user = (Professor) usuario.get(0);
		} else {
			user = (Seac) usuario.get(0);
		}
		
		user.header = mudaheader;
		user.sidebar = mudasidebar;
		session.put("header", user.header);
		session.put("sidebar", user.sidebar);
		user.save();
		Application.index();
	}
    
}
