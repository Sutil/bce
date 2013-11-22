package br.com.biblioteca.app.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.biblioteca.app.bean.AutorBean;

import com.google.common.base.Objects;
import com.google.common.base.Strings;

@Entity
public class Autor implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	private String sobrenome;
	
	Autor(){
		
	}
	
	private Autor(String nome, String sobrenome){
		this.nome = nome;
		this.sobrenome = sobrenome;
	}
	
	public static Autor newIntance(String nome, String sobrenome){
		checkArgument(!Strings.isNullOrEmpty(nome));
		checkArgument(!Strings.isNullOrEmpty(sobrenome));
		return new Autor(nome, sobrenome);
	}
	
	private Autor(AutorBean bean){
		this.id = bean.getId();
		this.nome = bean.getNome();
		this.sobrenome = bean.getSobrenome();
	}
	
	public static Autor newInstance(AutorBean bean){
		checkNotNull(bean);
		checkArgument(!Strings.isNullOrEmpty(bean.getNome()));
		checkArgument(!Strings.isNullOrEmpty(bean.getSobrenome()));
		return new Autor(bean);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Autor){
			Autor other = (Autor) obj;
			return Objects.equal(this.nome, other.nome);
			
			
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(nome);
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}
	

}
