package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import play.db.jpa.Model;
import play.libs.Crypto;
@Entity
public class Usuario extends Model {
	public String nome;
	public String email;
	public String senha;
	public String matricula;
	public String tipoVinculo;
	public String url_foto_75x100;
	public String url_foto_150x200;
	
	public String header;
	public String sidebar;

}
