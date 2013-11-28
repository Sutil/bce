package br.com.biblioteca.app.bean;

import java.io.Serializable;

import org.joda.time.LocalDate;

import br.com.biblioteca.app.login.seguranca.Usuario;
import br.com.biblioteca.app.model.Obra;

public class EmprestimoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private Obra obra;
	private LocalDate retirada;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public LocalDate getRetirada() {
		return retirada;
	}

	public void setRetirada(LocalDate retirada) {
		this.retirada = retirada;
	}

}
