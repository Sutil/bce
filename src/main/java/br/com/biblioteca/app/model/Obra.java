package br.com.biblioteca.app.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import br.com.biblioteca.app.bean.ObraBean;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

@Entity
public class Obra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Type(type = "categoria")
	private Categoria categoria;
	
	private String titulo;
	
	@Type(type = "disposicaoParaEmprestimo")
	private DisposicaoParaEmprestimo disposicao;
	
	@ManyToMany
	@JoinTable(name = "obra_autor", joinColumns = @JoinColumn( name = "usuario_fk" ), inverseJoinColumns = @JoinColumn(name = "autor_fk") )
	private List<Autor> autores = Lists.newLinkedList();
	
	@OneToMany(mappedBy = "obra", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Exemplar> exemplares = Lists.newLinkedList();

	Obra() {
	}
	

	private Obra(Categoria categoria, String titulo, Autor autor, Autor... autores) {
		this.categoria = categoria;
		this.titulo = titulo;
		this.autores.add(autor);
		if (autores != null) {
			for (Autor a : autores) {
				this.autores.add(a);
			}
		}
	}

	public static Obra newInstance(Categoria categoria, String titulo, Autor autor, Autor... autores) {
		checkNotNull(categoria);
		checkArgument(!Strings.isNullOrEmpty(titulo));
		checkNotNull(autor);
		return new Obra(categoria, titulo, autor, autores);
	}
	
	private Obra(ObraBean bean){
		this.id = bean.getId();
		this.autores = bean.getAutores();
		this.categoria = bean.getCategoria();
		this.disposicao = bean.getDisposicao();
		this.exemplares = bean.getExemplares();
		this.titulo = bean.getTitulo();
		for(Exemplar e : exemplares){
			e.setObra(this);
		}
	}
	
	public static Obra newInstance(ObraBean bean){
		checkNotNull(bean);
		checkArgument(bean.getAutores() != null && !bean.getAutores().isEmpty(), "informe os autores!");
		checkNotNull(bean.getCategoria(), "Informe a categoria");
		checkNotNull(bean.getDisposicao(), "Informe se a obra está disponível ou não para empréstimo");
		checkArgument(!Strings.isNullOrEmpty(bean.getTitulo()), "Inform o título da Obra");
		return new Obra(bean);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Obra){
			Obra other = (Obra) obj;
			return Objects.equal(this.titulo, other.titulo);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(titulo);
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("titulo", titulo).toString();
	}
	
	public boolean isReservaDisponivel(){
		return DisposicaoParaEmprestimo.DISPONIVEL.equals(this.disposicao) &&
				getQuantidadeDisponiveis() == 0;
	}

	public Long getId() {
		return id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public String getTitulo() {
		return titulo;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public DisposicaoParaEmprestimo getDisposicao() {
		return disposicao;
	}

	public List<Exemplar> getExemplares() {
		return exemplares;
	}
	
	public int getQuantidadeDisponiveis(){
		int count = 0;
		for(Exemplar e: exemplares){
			if(!e.estaEmprestado()){
				count++;
			}
		}
		return count;
	}
	
	public int getQuantidadeEmprestados(){
		int count = 0;
		for(Exemplar e: exemplares){
			if(e.estaEmprestado()){
				count++;
			}
		}
		return count;
	}

}
