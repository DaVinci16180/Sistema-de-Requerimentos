package models;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;


@Entity
public class Requerimento extends Model{
	
	{
		this.status = "3"; 
	}
	
	public String tipo;
	public String codTipo;
	public Calendar data;
	public String status;
	// 3 = Pendente
	// 2 = Avaliação do professor
	// 1 = Deferido
	// 0 = Indeferido
	public String fotoAnexo;
	
	@ManyToOne
	@JoinColumn(name = "aluno_id")
	public Aluno aluno;
	
	
	
	
}
