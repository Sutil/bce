package br.com.biblioteca.app.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import br.com.biblioteca.app.model.Exemplar;
import br.com.biblioteca.app.repository.ExemplarRepository;

@Configurable
@FacesConverter(value="exemplarConverter", forClass=Exemplar.class)
public class ExemplarConverter implements Converter {
	
	@Autowired
	private ExemplarRepository exemplarRepository;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if(value != null){
			Long id = Long.parseLong(value);
			return exemplarRepository.findOne(id);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(value != null){
			Exemplar exemplar = (Exemplar) value;
			return String.valueOf(exemplar.getId());
		}
		return null;
	}

}
