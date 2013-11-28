package br.com.biblioteca.app.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.common.collect.Lists;

import br.com.biblioteca.app.bean.FiltroPesquisa;
import br.com.biblioteca.app.model.Exemplar;
import br.com.biblioteca.app.repository.ExemplarRepository;

@Controller
public class HomeController {
	
	@Autowired
	private ExemplarRepository exemplarRepository;
	
	public FiltroPesquisa newFiltro(){
		return new FiltroPesquisa();
	}
	
	public List<Exemplar> pesquisar(FiltroPesquisa filtro){
		List<Exemplar> resultado = Lists.newLinkedList();
		if(filtro.toPredicate() != null){
			resultado = exemplarRepository.findAll(filtro.toPredicate());
		}
		if(resultado.isEmpty()){
			addMessage(FacesMessage.SEVERITY_WARN, "Exemplares não encontrados", "Sua pesquisa não retornou nenhum resultado");
		}
 		return resultado;
	}
	
	private void addMessage(FacesMessage.Severity severity, String title, String message){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, title, message));
	}

}
