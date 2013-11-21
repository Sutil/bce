package br.com.biblioteca.app.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import br.com.biblioteca.app.obra.Autor;
import br.com.biblioteca.app.repository.AutorRepository;

@Configurable
@FacesConverter("autorConverter")
public class AutorConverter implements Converter {
	
	@Autowired
	private AutorRepository autorRepository;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if(value != null){
			Long id = Long.parseLong(value);
			return autorRepository.findOne(id);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(value != null){
			Autor autor = (Autor) value;
			return String.valueOf(autor.getId());
		}
		return null;
	}

}
