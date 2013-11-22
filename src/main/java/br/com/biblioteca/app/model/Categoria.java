package br.com.biblioteca.app.model;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public enum Categoria {

	LIVRO(1, "Livro"), 
	PERIODICO(2, "Periódico"), 
	ENCICLOPEDIA(3, "Enciclopédia"),
	CD(4, "CD"),
	DVD(5, "DVD"),
	BLUE_RAY(6, "Blue Ray"),
	FITA_CASSETE(7, "Fita Cassete"),
	DICIONARIO(8, "Dicionario"),
	MAPA(9, "Mapa");
	
	private int codigo;
	private String descricao;
	
	private Categoria(int codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	private static final Map<Integer, Categoria> mapValues;
	static{
		Builder<Integer, Categoria> builder = ImmutableMap.builder();
		for(Categoria c : values()){
			builder.put(c.getCodigo(), c);
		}
		mapValues = builder.build();
	}
	
	public static Map<Integer, Categoria> getMapvalues() {
		return mapValues;
	}
	
	public static Categoria fromCodigo(Integer codigo){
		return mapValues.get(codigo);
	}
 	

}
