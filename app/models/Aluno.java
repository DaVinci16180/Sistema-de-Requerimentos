package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
@Entity
public class Aluno extends Usuario {
	public String curso;
	public String periodo;
	
	// Instance Initialization Block
	{
		this.nivel = 1;
	}
	
	@OneToMany(mappedBy="aluno")
	public List<Requerimento> requerimentos;
	

}
