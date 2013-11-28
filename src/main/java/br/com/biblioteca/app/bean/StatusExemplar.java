package br.com.biblioteca.app.bean;

import java.util.Map;

import com.google.common.collect.Maps;

public enum StatusExemplar {
	
		EMPRESTADO("Empresatado"), DISPONIVEL("Disponivel");
		
		private StatusExemplar(String value){
			this.value = value;
		}
		
		private String value;
		
		public String getValue() {
			return value;
		}
		
		private static Map<String , StatusExemplar> valueMap;
		static{
			Map<String, StatusExemplar> builder = Maps.newLinkedHashMap();
			for(StatusExemplar s : values()){
				builder.put(s.value, s);
			}
			valueMap = builder;
		}
		
		public static Map<String, StatusExemplar> getValueMap() {
			return valueMap;
		}

}
