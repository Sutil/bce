package br.com.biblioteca.app.bean;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

import br.com.biblioteca.app.model.Autor;

public class AutorBean implements Serializable {
	

	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;

	private String sobrenome;
	
	public AutorBean(){}
	
	private AutorBean(Autor autor){
		this.id = autor.getId();
		this.nome = autor.getNome();
		this.sobrenome = autor.getSobrenome();
	}
	
	public static AutorBean newInstance(Autor autor){
		checkNotNull(autor);
		return new AutorBean(autor);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	

}
