package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
@Entity
public class Aluno extends Model {
	public String nome;
	public long matricula;
	public String curso;
	public int periodo;
	public String telefone;
	public String email;

}
