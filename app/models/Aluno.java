package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import controllers.ReqEspeciais;
import play.db.jpa.Model;
@Entity
public class Aluno extends Usuario {
	public String curso;
	public String periodo;
	
	
	@OneToMany(mappedBy="aluno")
	public List<Requerimento> requerimentos;
	
	@OneToMany(mappedBy="aluno")
	public List<ReqEspecial> reqEspeciais;

}
