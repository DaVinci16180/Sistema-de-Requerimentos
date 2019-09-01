package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Tema extends Model {
	
	public String header = "app-header header-shadow";
	public String sidebar = "app-sidebar sidebar-shadow";
	
}
