package br.com.biblioteca.app.model;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public enum DisposicaoParaEmprestimo {
	
	DISPONIVEL("Disponível"),
	INDISPONIVEL("Indisponível");
	
	private String descricao;
	
	private DisposicaoParaEmprestimo(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	private static final Map<String, DisposicaoParaEmprestimo> mapValue;
	
	static {
		Builder<String, DisposicaoParaEmprestimo> builder = ImmutableMap.builder();
		for(DisposicaoParaEmprestimo d : values()){
			builder.put(d.getDescricao(), d);
		}
		mapValue = builder.build();
	}
	
	public static DisposicaoParaEmprestimo fromString(String value){
		return mapValue.get(value);
	}
	
	public static Map<String, DisposicaoParaEmprestimo> getMapValues(){
		return mapValue;
	}

}
