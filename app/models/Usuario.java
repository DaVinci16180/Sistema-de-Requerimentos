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
	public int nivel;
	
	public String header;
	public String sidebar;
	
	public void setSenha(String s) {
		senha = Crypto.passwordHash(s);
	}
	
}
