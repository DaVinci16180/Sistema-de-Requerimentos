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
		if (session.get("usuario.tipo").equals("Aluno")) {
			requerimentos = Requerimento.find("professor.nome like ?1 and aluno.email like ?2", "%"+pesquisa+"%", session.get("usuario.email")).fetch();
		} else if (session.get("usuario.tipo").equals("Professor")) {
			requerimentos = Requerimento.find("aluno.nome like ?1 and professor.email like ?2", "%"+pesquisa+"%", session.get("usuario.email")).fetch();
		} else {
			requerimentos = Requerimento.find("aluno.nome like ?1 or professor.nome like ?1", "%"+pesquisa+"%").fetch();
			alunos = Aluno.find("nome like ?1 or email like ?1 or matricula like ?1", "%"+pesquisa+"%").fetch();
			professores = Professor.find("nome like ?1 or email like ?1 or matricula like ?1", "%"+pesquisa+"%").fetch();
		}
		render(requerimentos, alunos, professores);
	}
    
    public static void personalizar(String mudaheader, String mudasidebar) {
		
		if (session.get("usuario.tipo").equals("adm") == false) {
			Usuario user = Usuario.find("byEmail", session.get("usuario.email")).first();
			user.header = mudaheader;
			user.sidebar = mudasidebar;
			session.put("header", user.header);
			session.put("sidebar", user.sidebar);
			user.save();
		} else {
			Administrador adm = Administrador.find("matricula = ?", session.get("usuario.matricula")).first();
			adm.header = mudaheader;
			adm.sidebar = mudasidebar;
			session.put("header", adm.header);
			session.put("sidebar", adm.sidebar);
			adm.save();
		}
		
		Application.index();
	}
    
}
