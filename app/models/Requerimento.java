package models;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;


@Entity
public class Requerimento extends Model{
	
	public String tipo;
	public String disciplina;
	public Calendar data;
	
	
	@ManyToOne
	@JoinColumn(name = "aluno_id")
	public Aluno aluno;
	
	@ManyToOne
	@JoinColumn(name = "professor_id")
	public Professor professor;
	
	
}
