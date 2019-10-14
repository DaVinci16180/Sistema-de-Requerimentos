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
	
	@OneToMany(mappedBy="professor")
	public List<ReposicaoAtividade> requerimentos;
	
	@ManyToMany(mappedBy="professores")
	public List<Disciplina> disciplinas;

}
