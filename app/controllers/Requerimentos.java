package controllers;

import java.io.File;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;


import models.Aluno;
import models.Disciplina;
import models.JustificativaFalta;
import models.Professor;
import models.ReposicaoAtividade;
import models.Requerimento;
import models.Usuario;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Requerimentos extends Controller{
	
	public static void form() {
		Aluno aluno = Aluno.find("matricula = ?", session.get("usuario.matricula")).first();
		List<Disciplina> disciplinas = Disciplina.find("curso like ?1 or curso like ?2", "Todos", aluno.curso).fetch();
		render(aluno, disciplinas);
	}
	
	
	public static void reposicao(Long idDisciplina) {
		if (idDisciplina == null) {
			flash.error("Selecione uma Disciplina!");
			form();
		} else {
			Aluno aluno = Aluno.find("matricula = ?", session.get("usuario.matricula")).first();
			Disciplina disciplina = Disciplina.findById(idDisciplina);
			List<Professor> professores = disciplina.professores;
			Cache.set("disciplinaId", idDisciplina);
			render(aluno, professores);
			
		}
	}
	
	public static void salvar(Requerimento requerimento, Long idProfessor, File foto, Calendar dataLimite) {
		// Testa se existem campos em branco no formulário de criação de requerimentos
		if (requerimento.data == null || (idProfessor == null && requerimento.codTipo.equals("1")) || foto == null) {
			flash.error("Preencha Todos os Dados para Continuar!");
			// Se houverem campos em branco em um requerimento do tipo "Reposição de Atividades"
			if (requerimento.codTipo.equals("1")) { 
				Disciplina disciplina = Disciplina.findById(Cache.get("disciplinaId"));
				Cache.clear();
				reposicao(disciplina.id);
			// Se houverem campos em branco em um requerimento do tipo "Justificativa de Faltas"
			} else {
				form();
			}
		} else {
			if (requerimento.codTipo.equals("1")) {
				ReposicaoAtividade req = new ReposicaoAtividade();
				req.aluno = requerimento.aluno;
				req.data = requerimento.data;
				Professor professor = Professor.findById(idProfessor);
				req.professor = professor;
				req.fotoAnexo = foto.getName();
				req.save();
				new File("./uploads/" + req.id).mkdirs();
				foto.renameTo(new File("./uploads/" + req.id + "/" + foto.getName()));
				flash.success("Requerimento enviado com sucesso!");
			} else {
				JustificativaFalta req = new JustificativaFalta();
				req.aluno = requerimento.aluno;
				req.data = requerimento.data;
				if (dataLimite != null) {
					req.dataLimite = dataLimite;
				}
				req.fotoAnexo = foto.getName();
				req.save();
				new File("./uploads/" + req.id).mkdirs();
				foto.renameTo(new File("./uploads/" + req.id + "/" + foto.getName()));
				flash.success("Requerimento enviado com sucesso!");
			}
			listar();
		}
	}
	
	public static void listar() {
		
		Usuario usuario = Usuario.find("matricula = ?", session.get("usuario.matricula")).first();
		if (session.get("usuario.tipo").equals("Aluno")) {
			List<Requerimento> requerimentos;
			Aluno aluno = (Aluno) usuario;
			requerimentos = aluno.requerimentos;
			Collections.sort(requerimentos, new Comparator<Requerimento>() {
				@Override
				public int compare(Requerimento r1, Requerimento r2) {
					return r2.id.compareTo(r1.id);
				}
			});
			render(requerimentos);
		} else if (session.get("usuario.tipo").equals("Professor")) {
			List<ReposicaoAtividade> requerimentos;
			Professor professor = (Professor) usuario;
			requerimentos = professor.requerimentos;
			List<Requerimento> filtro = Requerimento.find("status = ?1 or status = ?2 or status = ?3", "0", "1", "2").fetch();
			requerimentos.retainAll(filtro);
			Collections.sort(requerimentos, new Comparator<Requerimento>() {
				@Override
				public int compare(Requerimento r1, Requerimento r2) {
					return r2.id.compareTo(r1.id);
				}
			});
			render(requerimentos);
		} else {
			List<Requerimento> requerimentos = Requerimento.findAll();
			Collections.sort(requerimentos, new Comparator<Requerimento>() {
				@Override
				public int compare(Requerimento r1, Requerimento r2) {
					return r2.id.compareTo(r1.id);
				}
			});
			render(requerimentos);
		}
	}
	
	public static void detalhes(Long id) {
		Requerimento req = Requerimento.findById(id);
		String mes = "";
		String data;
		if (req.tipo.equals("Justificativa de Faltas")) {
			JustificativaFalta requerimento = Requerimento.findById(id);
			// Envio da data do requerimento
			mes = "";
			// Conversão do mês
			switch (requerimento.data.get(Calendar.MONTH)) {
			case 0:
				mes = "Janeiro";
				break;
			case 1:
				mes = "Fevereiro";
				break;
			case 2:
				mes = "Março";
				break;
			case 3:
				mes = "Abril";
				break;
			case 4:
				mes = "Maio";
				break;
			case 5:
				mes = "Junho";
				break;
			case 6:
				mes = "Julho";
				break;
			case 7:
				mes = "Agosto";
				break;
			case 8:
				mes = "Setembro";
				break;
			case 9:
				mes = "Outubro";
				break;
			case 10:
				mes = "Novembro";
				break;
			case 11:
				mes = "Dezembro";
				break;
			}
			// Envio da data limite, caso haja uma
			String mesLimite = "";
			String dataLimite = "";
			if (requerimento.dataLimite != null) {
				// Conversão do mês
				switch (requerimento.dataLimite.get(Calendar.MONTH)) {
				case 0:
					mesLimite = "Janeiro";
					break;
				case 1:
					mesLimite = "Fevereiro";
					break;
				case 2:
					mesLimite = "Março";
					break;
				case 3:
					mesLimite = "Abril";
					break;
				case 4:
					mesLimite = "Maio";
					break;
				case 5:
					mesLimite = "Junho";
					break;
				case 6:
					mesLimite = "Julho";
					break;
				case 7:
					mesLimite = "Agosto";
					break;
				case 8:
					mesLimite = "Setembro";
					break;
				case 9:
					mesLimite = "Outubro";
					break;
				case 10:
					mesLimite = "Novembro";
					break;
				case 11:
					mesLimite = "Dezembro";
					break;
				}
				// Criação da String da data limite
				dataLimite = requerimento.dataLimite.get(Calendar.DAY_OF_MONTH) + " de " + mesLimite + " de " + requerimento.data.get(Calendar.YEAR);
			}
			// Criação da String da data
			data = requerimento.data.get(Calendar.DAY_OF_MONTH) + " de " + mes + " de " + requerimento.data.get(Calendar.YEAR);
			render(requerimento, data, dataLimite);
		} else {
			ReposicaoAtividade requerimento = Requerimento.findById(id);
			// Envio da data do requerimento
			mes = "";
			// Conversão do mês
			switch (requerimento.data.get(Calendar.MONTH)) {
			case 0:
				mes = "Janeiro";
				break;
			case 1:
				mes = "Fevereiro";
				break;
			case 2:
				mes = "Março";
				break;
			case 3:
				mes = "Abril";
				break;
			case 4:
				mes = "Maio";
				break;
			case 5:
				mes = "Junho";
				break;
			case 6:
				mes = "Julho";
				break;
			case 7:
				mes = "Agosto";
				break;
			case 8:
				mes = "Setembro";
				break;
			case 9:
				mes = "Outubro";
				break;
			case 10:
				mes = "Novembro";
				break;
			case 11:
				mes = "Dezembro";
				break;
			}
			data = requerimento.data.get(Calendar.DAY_OF_MONTH) + " de " + mes + " de " + requerimento.data.get(Calendar.YEAR);
			render(requerimento, data);
		}
	}
	
	public static void deferir(Long id) {
		if (session.get("usuario.tipo").equals("adm")) {
			Requerimento requerimento = Requerimento.findById(id);
			if (requerimento.tipo.equals("Reposição de Atividades") && (requerimento.status.equals("3") || requerimento.status.equals("0"))) {
				requerimento.status = "2";
			} else {
				requerimento.status = "1";
			}
			requerimento.save();
			render("Requerimentos/detalhes.html", requerimento);
		} else {
			Requerimento requerimento = Requerimento.findById(id);
			requerimento.status = "1";
			requerimento.save();
			render("Requerimentos/detalhes.html", requerimento);
		}
	}
	
	public static void indeferir(Long id) {
		Requerimento requerimento = Requerimento.findById(id);
		requerimento.status = "0";
		requerimento.save();
		render("Requerimentos/detalhes.html", requerimento);
	}
}
