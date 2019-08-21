package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Requerimento extends Model{
	public Long aluno_id;
	public Long professor_id;
	public Long id;
	public Long tipo_id;
	
}
