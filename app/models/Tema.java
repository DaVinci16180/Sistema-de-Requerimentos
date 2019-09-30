package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Tema extends Model {
	
	public String header;
	public String sidebar;
	{
		header = "app-header header-shadow";
		sidebar = "app-sidebar sidebar-shadow";
	}
	
}
