package br.com.biblioteca.app.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.google.common.base.Objects;

@Entity
public class Multa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final BigDecimal multa = new BigDecimal("2.75");
	
	@Id
	@GeneratedValue
	private Long id;
	
	private BigDecimal valor;
	
	@OneToOne
	@JoinColumn(name = "emprestimo_fk")
	private Emprestimo emprestimo;
	
	private Multa(Emprestimo emprestimo){
		this.emprestimo = emprestimo;
		this.valor = multa.multiply(new BigDecimal(emprestimo.atraso()));
	}
	
	public static Multa newInstance(Emprestimo emprestimo) {
		checkNotNull(emprestimo, "informe um emprestimo para gerar a multa.");
		return new Multa(emprestimo);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Multa){
			Multa other = (Multa) obj;
			return Objects.equal(this.emprestimo, other.emprestimo);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(emprestimo);
	}
	
	public Emprestimo getEmprestimo() {
		return emprestimo;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public Long getId() {
		return id;
	}

	

}
