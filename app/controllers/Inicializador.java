package controllers;

import models.Aluno;
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
	}
}
