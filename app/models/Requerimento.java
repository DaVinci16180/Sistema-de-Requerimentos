package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Requerimento extends Model{
	public String aluno;
	public String professor;
	public String tipo;
	
}
