package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;


@Entity
public class ReqEspecial extends Model {
	
	public String tipo;
	public String texto;
	
	@ManyToOne
	@JoinColumn(name = "aluno_id")
	public Aluno aluno;

}
