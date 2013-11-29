package br.com.biblioteca.app.bean;

import java.io.Serializable;

import br.com.biblioteca.app.model.Obra;

public class ItemRelatorioMaisRetiradas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Obra obra;
	private int retiradas;
	
	public Obra getObra() {
		return obra;
	}
	
	public int getRetiradas() {
		return retiradas;
	}

}
