package br.com.biblioteca.app.controller.emprestimo;

import java.io.Serializable;

import br.com.biblioteca.app.login.seguranca.QUsuario;
import br.com.biblioteca.app.model.QEmprestimo;

import com.google.common.base.Strings;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;

public class FiltroRelatorioEmprestimo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String usuario;
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Predicate toQuery() {
		BooleanBuilder builder = new BooleanBuilder();
		if(!Strings.isNullOrEmpty(usuario)){
			builder.and(QEmprestimo.emprestimo.usuario.login.containsIgnoreCase(usuario));
		}
		return builder;
	}

}
