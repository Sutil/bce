package br.com.biblioteca.app.bean;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.List;

import br.com.biblioteca.app.obra.Autor;
import br.com.biblioteca.app.obra.Categoria;
import br.com.biblioteca.app.obra.DisposicaoParaEmprestimo;
import br.com.biblioteca.app.obra.Exemplar;
import br.com.biblioteca.app.obra.Obra;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public class ObraBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Categoria categoria;

	private String titulo;
	
	private DisposicaoParaEmprestimo disposicao;
	
	private List<Autor> autores = Lists.newLinkedList();
	
	private List<Exemplar> exemplares = Lists.newLinkedList();
	
	private int numeroExemplares;
	
	public ObraBean(){}
	
	public ObraBean(Obra obra){
		checkNotNull(obra);
		this.id = obra.getId();
		this.categoria = obra.getCategoria();
		this.titulo = obra.getTitulo();
		this.disposicao = obra.getDisposicao();
		this.autores = obra.getAutores();
		this.exemplares = obra.getExemplares();
		this.numeroExemplares = this.exemplares.size();
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", id).add("categoria", categoria)
				.add("titulo", titulo).toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public DisposicaoParaEmprestimo getDisposicao() {
		return disposicao;
	}

	public void setDisposicao(DisposicaoParaEmprestimo disposicao) {
		this.disposicao = disposicao;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public List<Exemplar> getExemplares() {
		return exemplares;
	}

	public void setExemplares(List<Exemplar> exemplares) {
		this.exemplares = exemplares;
	}
	
	public int getNumeroExemplares() {
		return numeroExemplares;
	}
	
	public void setNumeroExemplares(int numeroExemplares) {
		this.numeroExemplares = numeroExemplares;
	}
	
	

}
