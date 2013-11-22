package br.com.biblioteca.app.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import br.com.biblioteca.app.bean.AutorBean;
import br.com.biblioteca.app.model.Autor;
import br.com.biblioteca.app.repository.AutorRepository;

@Controller
public class AutorController {
	
	@Autowired
	private AutorRepository autorRepository;
	
	public List<Autor> listar(){
		return autorRepository.findAll();
	}
	
	public AutorBean novo(){
		return new AutorBean();
	}
	
	public void save(AutorBean bean){
		Autor autor = Autor.newInstance(bean);
		Autor saved = autorRepository.save(autor);
		if(saved != null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(saved.getSobrenome(), "Salvo com sucesso"));
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Não foi possível salvar o autor"));
		}
	}
	
	public AutorBean editar(Autor autor){
		return AutorBean.newInstance(autor);
	}
	
	@Transactional
	public void delete(Autor autor){
		autorRepository.delete(autor);
	}
	
	public void teste(){
		System.out.println("teste");
	}

}
