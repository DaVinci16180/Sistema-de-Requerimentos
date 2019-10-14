package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class ReposicaoAtividade extends Requerimento {
	
	{
		this.tipo = "Reposição de Atividades";
	}
	@ManyToOne
	@JoinColumn(name = "professor_id")
	public Professor professor;
}
