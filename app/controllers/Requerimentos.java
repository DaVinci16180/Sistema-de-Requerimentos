package controllers;

import java.util.List;

import models.Aluno;
import models.Requerimento;
import play.mvc.Controller;

public class Requerimentos extends Controller{
	
	public static void form() {
		render();
	}
	
	public static void salvar(Requerimento requerimento) {
		requerimento.save();
		listar();
	}
	
	public static void listar() {
		List<Requerimento> requerimentos = Requerimento.findAll();
		render(requerimentos);
	}
	
	public static void deletar(Long id) {
		Requerimento requerimento = Requerimento.findById(id);
		requerimento.delete();
		listar();
	}
	
	public static void editar(Long id) {
		Requerimento requerimento = Requerimento.findById(id);
		render("Requerimentos/form.html", requerimento);
	}
}
