package br.com.biblioteca.app.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.google.common.base.Strings;

import br.com.biblioteca.app.login.seguranca.Permissao;

@FacesConverter(value="permissaoConverter", forClass= Permissao.class)
public class PermissaoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if(!Strings.isNullOrEmpty(value)){
			return Permissao.fromCodigo(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(value != null){
			Permissao permissao = (Permissao) value;
			return permissao.getCodigo();
		}
		return null;
	}

}
