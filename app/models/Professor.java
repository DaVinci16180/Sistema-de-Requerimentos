package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
@Entity
public class Professor extends Usuario{
	public String disciplina;
	
	
	@OneToMany(mappedBy="professor")
	public List<Requerimento> requerimentos;
	
	@ManyToMany(mappedBy="professores")
	public List<Disciplina> disciplinas;

}
