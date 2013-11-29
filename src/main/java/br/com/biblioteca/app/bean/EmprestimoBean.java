package br.com.biblioteca.app.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.google.common.base.Strings;

import br.com.biblioteca.app.login.seguranca.Usuario;
import br.com.biblioteca.app.model.DisposicaoParaEmprestimo;
import br.com.biblioteca.app.model.Exemplar;

public class EmprestimoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private Exemplar exemplar;
	
	public boolean podeGerarEmprestimo(){
		StringBuilder sb = new StringBuilder();
		boolean a = usuario != null &&
				exemplar != null && 
				exemplar.getObra().getDisposicao().equals(DisposicaoParaEmprestimo.DISPONIVEL) &&
				!exemplar.estaEmprestado();
		if(usuario == null){
			sb.append("Usuário não selecionado\n");
		}
		if(exemplar == null){
			sb.append("exemplar não selecionado\n");
		}
		if(exemplar != null && exemplar.getObra().getDisposicao().equals(DisposicaoParaEmprestimo.INDISPONIVEL)){
			sb.append("Esta obra não está disponível para empréstimo\n");
		}
		if(exemplar != null && exemplar.estaEmprestado()){
			sb.append("Este exemplar está emprestado\n");
		}
		String msg = sb.toString();
		if(!Strings.isNullOrEmpty(msg)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, ""));
		}
		return a;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Exemplar getExemplar() {
		return exemplar;
	}

	public void setExemplar(Exemplar exemplar) {
		this.exemplar = exemplar;
	}

}
