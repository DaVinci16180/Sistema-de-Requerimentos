package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
@Entity
public class Aluno extends Usuario {
	
	@OneToMany(mappedBy="aluno")
	public List<Requerimento> requerimentos;
	

}
