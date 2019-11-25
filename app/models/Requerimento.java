package models;

import java.io.File;
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
		this.lido = true;
	}
	
	public String tipo;
	public String codTipo;
	@Required
	public Calendar data;
	public String status;
	// 3 = Pendente
	// 2 = Avaliação do professor
	// 1 = Deferido
	// 0 = Indeferido
	@Required
	public String fotoAnexo;
	public boolean lido;
	@ManyToOne
	@JoinColumn(name = "aluno_id")
	public Aluno aluno;
	
	
	
	
}
