package br.com.biblioteca.app.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import br.com.biblioteca.app.login.seguranca.Usuario;

@Entity
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "obra_fk")
	private Obra obra;
	
	@Type(type="localDate")
	private LocalDate reserva;
	
	private LocalDate disponibilidade;
	
	private LocalDate expiracao;
	
	Reserva(){}
	
	private Reserva(Usuario usuario, Obra obra) {
		this.usuario = usuario;
		this.obra = obra;
		this.reserva = new LocalDate();
	}
	
	public static Reserva newInstance(Usuario usuario, Obra obra){
		checkNotNull(usuario, "Usuário não pode fazer reserva.");
		checkNotNull(obra, "informe uma obra para esta reserva");
		for(Exemplar e: obra.getExemplares()){
			checkArgument(e.estaEmprestado(), "Há exemplar(es) diponivel(eis) desta obra. Você pode efetuar o emprestimo sem reserva!");
		}
		return new Reserva(usuario, obra);
	}
	
	public Long getId() {
		return id;
	}
	
	public Obra getObra() {
		return obra;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public LocalDate getExpiracao() {
		return expiracao;
	}
	
	public LocalDate getDisponibilidade() {
		return disponibilidade;
	}
	
	public LocalDate getReserva() {
		return reserva;
	}
	
	public boolean expirada(){
		if(disponibilidade != null && expiracao != null){
			return expiracao.isBefore(new LocalDate());
		}
		return false;
	}
	

}
