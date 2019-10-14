package models;

import java.util.Calendar;

import javax.persistence.Entity;
@Entity
public class JustificativaFalta extends Requerimento {
	
	{
		this.tipo = "Justificativa de Faltas";
	}
	
	public Calendar dataLimite;
	
}
