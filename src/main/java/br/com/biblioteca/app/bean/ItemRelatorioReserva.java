package br.com.biblioteca.app.bean;

import java.io.Serializable;

import com.google.common.base.Objects;

import br.com.biblioteca.app.model.Obra;

public class ItemRelatorioReserva implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Obra obra;
	private int reservas;
	
	public ItemRelatorioReserva(Obra obra, int reservas){
		this.obra = obra;
		this.reservas = reservas;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ItemRelatorioReserva){
			ItemRelatorioReserva other = (ItemRelatorioReserva) obj;
			return Objects.equal(this.obra, other.obra);
		}
		return false;
	}
	
	public Obra getObra() {
		return obra;
	}
	
	public int getReservas() {
		return reservas;
	}
	
	public void addReservas(){
		reservas++;
	}

}
