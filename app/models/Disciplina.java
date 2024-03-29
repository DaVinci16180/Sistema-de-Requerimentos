package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Disciplina extends Model {
	@Required
	public String nome;
	@Required
	public String curso;
	
	@ManyToMany
	@JoinTable(name="professores_disciplinas")
	public List<Professor> professores;
}
