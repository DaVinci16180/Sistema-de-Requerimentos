package models;

import java.util.Map;

import play.db.jpa.Model;

public class DadosSUAP extends Model {
	
	public String matricula;
    public String senha;
    public String email;
    public String nome_usual;
	public String tipo_vinculo;
	public String url_foto_75x100;
	public String url_foto_150x200;
	public Map<String, Object> vinculo;
	public String categoria;
}
