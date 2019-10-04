package models;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;


@Entity
public class Requerimento extends Model{
	
	{
		this.status = "2"; 
	}
	
	public String tipo;
	public String disciplina;
	public Calendar data;
	public String status;
	// 2 = Pendente
	// 1 = Deferido
	// 0 = Indeferido
	
	@ManyToOne
	@JoinColumn(name = "aluno_id")
	public Aluno aluno;
	
	@ManyToOne
	@JoinColumn(name = "professor_id")
	public Professor professor;
	
	
}
