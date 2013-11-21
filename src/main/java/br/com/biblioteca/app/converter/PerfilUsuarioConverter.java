package br.com.biblioteca.app.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import br.com.biblioteca.app.login.seguranca.PerfilUsuario;
import br.com.biblioteca.app.repository.PerfilUsuarioRepository;

@Configurable
@FacesConverter(value = "perfilUsuarioConverter", forClass=PerfilUsuario.class)
public class PerfilUsuarioConverter implements Converter {
	
	@Autowired
	private PerfilUsuarioRepository perfilUsuarioRepository;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if(value != null){
			Long id = Long.parseLong(value);
			return perfilUsuarioRepository.findOne(id);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(value != null){
			PerfilUsuario perfil = (PerfilUsuario) value;
			return String.valueOf(perfil.getId());
		}
		return null;
	}

}
