package br.com.biblioteca.app.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import br.com.biblioteca.app.login.seguranca.Usuario;
import br.com.biblioteca.app.login.seguranca.repository.UsuarioRepository;

@Configurable
@FacesConverter(value="converter", forClass=Usuario.class)
public class UsuarioConverter implements Converter {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if(value != null){
			Long id = Long.parseLong(value);
			return usuarioRepository.findOne(id);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(value != null){
			Usuario usuario = (Usuario) value;
			return String.valueOf(usuario.getId());
		}
		return null;
	}

}
