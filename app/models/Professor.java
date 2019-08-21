package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
@Entity
public class Professor extends Model{
	public String nome;
	public long matricula;
	public String disciplina;
	public String telefone;
	public String email;

}
