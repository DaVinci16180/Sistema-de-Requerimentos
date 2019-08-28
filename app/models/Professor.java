package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
@Entity
public class Professor extends Usuario{
	public String disciplina;
	
	// Instance Initialization Block
	{
	this.nivel = 2;
	}
	
	@OneToMany
	@JoinColumn(name = "professor_id")
	public List<Requerimento> requerimentos;

}
