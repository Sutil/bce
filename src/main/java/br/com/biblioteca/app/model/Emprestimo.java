package br.com.biblioteca.app.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import br.com.biblioteca.app.bean.EmprestimoBean;
import br.com.biblioteca.app.login.seguranca.Usuario;

import com.google.common.base.Objects;

@Entity
public class Emprestimo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "usuario_fk")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "exemplar_fk")
	private Exemplar exemplar;

	@Type(type = "localDate")
	private LocalDate retirada;

	@Type(type = "localDate")
	private LocalDate previsaoDeDevolucao;

	@Type(type = "localDate")
	private LocalDate devolucao;
	
	public Emprestimo() {}
	
	private Emprestimo(EmprestimoBean bean){
		this.usuario = bean.getUsuario();
		this.exemplar = bean.getExemplar();
		this.retirada = new LocalDate();
		this.previsaoDeDevolucao = retirada.plusDays(usuario.getPerfil().getDuracaoDoEmprestimo());
	}

	public static Emprestimo newInstance(EmprestimoBean bean) {
		checkNotNull(bean.getUsuario(), "usuário não pode ser nulo");
		checkNotNull(bean.getExemplar(), "exemplar não pode ser nulo");
		checkArgument(bean.getUsuario().getPerfil().getCotaEmprestimo() > 0, "cota de empréstimo do usuário deve ser maior que zero" );
		return new Emprestimo(bean);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Emprestimo) {
			Emprestimo other = (Emprestimo) obj;
			return Objects.equal(this.usuario, other.usuario) && Objects.equal(this.exemplar, other.exemplar) && Objects.equal(this.retirada, other.retirada);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(usuario, exemplar, retirada);
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", id)
				.add("user", usuario)
				.add("retirada", retirada)
				.add("devolucao", devolucao).toString();
	}
	
	public Multa devolver(){
		Multa multa = null;
		if(isAtrasado()){
			multa = Multa.newInstance(this);
		}
		this.exemplar.devolver();
		this.devolucao = new LocalDate();
		return multa;
	}

	public Long getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Exemplar getExemplar() {
		return exemplar;
	}

	public LocalDate getRetirada() {
		return retirada;
	}

	public LocalDate getPrevisaoDeDevolucao() {
		return previsaoDeDevolucao;
	}

	public LocalDate getDevolucao() {
		return devolucao;
	}
	
	public boolean isDevolvido(){
		return this.devolucao != null;
	}
	
	public boolean isAtrasado(){
		long dias = usuario.getPerfil().getDuracaoDoEmprestimo().longValue();
		return devolucao == null && atraso() > dias; 
	}
	
	public long atraso(){
		long milis;
		if(devolucao != null){
			milis = devolucao.toDate().getTime() - retirada.toDate().getTime() ;
		}
		else{
			milis = new Date().getTime() - retirada.toDate().getTime();
		}
		return milis / (1000*60*60*24);
	}

	public Multa preparaDevolucao() {
		if(isAtrasado()){
			Multa multa = Multa.newInstance(this);
			return multa;
		}
		return null;
	}

}
