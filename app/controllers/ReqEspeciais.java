package controllers;

import java.util.Arrays;

import java.util.List;

import enums.TiposEspeciais;
import models.Aluno;
import models.Professor;
import models.ReqEspecial;
import models.Requerimento;
import models.Usuario;
import play.mvc.Controller;
import play.mvc.With;
import enums.TiposEspeciais;

@With(Seguranca.class)
public class ReqEspeciais extends Controller{
	
	public static void form() {
		List<TiposEspeciais> tiposEsp = Arrays.asList(TiposEspeciais.values());
		Aluno aluno = Aluno.find("matricula = ?", session.get("usuario.matricula")).first();
		render(aluno, tiposEsp);
	}
	
	public static void salvar(ReqEspecial reqEspecial) {
		reqEspecial.save();
		flash.success("Requerimento enviado com sucesso!");
		listar();
	}
	
	public static void listar() {
		List<ReqEspecial> reqEspeciais;
		Usuario usuario = Usuario.find("matricula = ?", session.get("usuario.matricula")).first();
		if (session.get("usuario.tipo").equals("Aluno")) {
			Aluno aluno = (Aluno) usuario;
			reqEspeciais = aluno.reqEspeciais;
		} else {
			reqEspeciais = Requerimento.findAll();
		}
		render(reqEspeciais);
	}
	
	public static void deletar(Long id) {
		ReqEspecial reqEspecial = ReqEspecial.findById(id);
		reqEspecial.delete();
		flash.success("Requerimento removido com sucesso!");
		listar();
	}
	
}
