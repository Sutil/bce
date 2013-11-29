package br.com.biblioteca.app.bean;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

import br.com.biblioteca.app.model.Emprestimo;
import br.com.biblioteca.app.model.Multa;

public class DevolucaoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Emprestimo emprestimo;
	
	private Multa multa;
	
	private boolean pago;
	
	private DevolucaoBean(Emprestimo emprestimo){
		this.emprestimo = emprestimo;
	}
	
	public static DevolucaoBean newInstance(Emprestimo emprestimo){
		checkNotNull(emprestimo);
		return new DevolucaoBean(emprestimo);
	}
	
	public Emprestimo getEmprestimo() {
		return emprestimo;
	}
	
	public Multa getMulta() {
		return multa;
	}
	
	public void devolver(){
		this.multa = emprestimo.preparaDevolucao();
		System.out.println(multa);
	}
	
	public boolean isPago() {
		return pago;
	}
	
	public void setPago(boolean pago) {
		this.pago = pago;
	}
	
	

}
