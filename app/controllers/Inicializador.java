package controllers;

import models.Aluno;
import models.Curso;
import models.Disciplina;
import models.Professor;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

@OnApplicationStart
public class Inicializador extends Job {
	
	public void doJob() {
		if (Aluno.count() == 0 && Professor.count() == 0) {
			Fixtures.loadModels("init-data.yml");
		}
		
//		Curso inf = new Curso();
//		inf.nome = "Técnico de Nível Médio em Informática";
//		inf.save();
//		Curso qm = new Curso();
//		qm.nome = "Técnico de Nível Médio em Química";
//		qm.save();
//		Curso rp = new Curso();
//		rp.nome = "Técnico de Nível Médio em Recursos Pesqueiros";
//		rp.save();
		
//		Disciplina matematica = new Disciplina();
//		matematica.nome = "Matemática";
//		matematica.cursos.add(inf);
//		matematica.cursos.add(qm);
//		matematica.cursos.add(rp);
//		matematica.save();
//		
//		Disciplina portugues = new Disciplina();
//		portugues.nome = "Português";
//		portugues.cursos.add(inf);
//		portugues.cursos.add(qm);
//		portugues.cursos.add(rp);
//		portugues.save();
//		
//		Disciplina historia = new Disciplina();
//		historia.nome = "História";
//		historia.cursos.add(inf);
//		historia.cursos.add(qm);
//		historia.cursos.add(rp);
//		historia.save();
//		
//		Disciplina inorganica = new Disciplina();
//		inorganica.nome = "Química Inorgânica";
//		inorganica.cursos.add(qm);
//		inorganica.save();
//		
//		Disciplina ppi = new Disciplina();
//		ppi.nome = "Programação para Internet";
//		ppi.cursos.add(inf);
//		ppi.save();
//		
//		Disciplina pesca = new Disciplina();
//		pesca.nome = "Introdução à Pesca";
//		matematica.cursos.add(rp);
//		pesca.save();
		
	}
}
