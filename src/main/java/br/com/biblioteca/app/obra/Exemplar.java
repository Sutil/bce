package br.com.biblioteca.app.obra;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.common.base.Objects;

@Entity
public class Exemplar implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	private Integer identificador;
	
	@ManyToOne
	@JoinColumn(name = "obra_pk" , nullable = false)
	private Obra obra;
	
	public Exemplar(){
	}
	
	
	private Exemplar(Integer identificador, Obra obra){
		this.identificador = identificador;
		this.obra = obra;
	}
	
	public static Exemplar newInstance(Integer identificador, Obra obra){
		checkNotNull(identificador);
		checkNotNull(obra);
		checkArgument(identificador > 0);
		return new Exemplar(identificador, obra);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Exemplar){
			Exemplar other = (Exemplar) obj;
			return Objects.equal(this.identificador, other.identificador) && 
					Objects.equal(this.obra, other.obra);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(identificador, obra);
	}
	
	public Long getId() {
		return id;
	}
	
	public Integer getIdentificador() {
		return identificador;
	}
	
	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}
	
	public Obra getObra() {
		return obra;
	}
	

}
