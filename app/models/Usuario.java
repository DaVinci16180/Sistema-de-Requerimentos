package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
import play.libs.Crypto;
@Entity
public abstract class Usuario extends Model {
	public String nome;
	public String email;
	public String senha;
	public String matricula;
	public int nivel;
	
	public void setSenha(String s) {
		senha = Crypto.passwordHash(s);
	}
	
}
