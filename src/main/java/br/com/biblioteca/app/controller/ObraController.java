package br.com.biblioteca.app.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.DataModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.faces.model.OneSelectionTrackingListDataModel;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import br.com.biblioteca.app.bean.ObraBean;
import br.com.biblioteca.app.obra.Autor;
import br.com.biblioteca.app.obra.Categoria;
import br.com.biblioteca.app.obra.DisposicaoParaEmprestimo;
import br.com.biblioteca.app.obra.Exemplar;
import br.com.biblioteca.app.obra.Obra;
import br.com.biblioteca.app.obra.QAutor;
import br.com.biblioteca.app.obra.QExemplar;
import br.com.biblioteca.app.repository.AutorRepository;
import br.com.biblioteca.app.repository.ExemplarRepository;
import br.com.biblioteca.app.repository.ObraRepository;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.mysema.query.BooleanBuilder;

@Controller
public class ObraController {
	
	@Autowired
	private ObraRepository obraRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private ExemplarRepository exemplarRepository;
	
	public ObraBean novo(){
		return new ObraBean();
	}
	
	public List<Categoria> getCategorias(){
		return new ArrayList<Categoria>(Categoria.getMapvalues().values());
	}
	
	public List<DisposicaoParaEmprestimo> getDisposicoes(){
		return new ArrayList<DisposicaoParaEmprestimo>(DisposicaoParaEmprestimo.getMapValues().values());
	}
	
	public List<Autor> completeAutores(String value){
		List<Autor> autores = Lists.newLinkedList();
		if(!Strings.isNullOrEmpty(value)){
			BooleanBuilder builderBusca = new BooleanBuilder();
			builderBusca.or(QAutor.autor.nome.containsIgnoreCase(value));
			builderBusca.or(QAutor.autor.sobrenome.containsIgnoreCase(value));
			BooleanBuilder builder = new BooleanBuilder();
			builder.and(builderBusca);
			autores = autorRepository.findAll(builder);
		}
		return autores;
	}
	
	public List<Obra> pesquisar(){
		return obraRepository.findAll();
	}
	
	public List<Exemplar> addExemplar(ObraBean bean, DataModel<Exemplar> model){
		Iterator<Exemplar> iterator = model.iterator();
		List<Exemplar> exemplares = Lists.newArrayList();
		while(iterator.hasNext()){
			exemplares.add(iterator.next());
		}
		Exemplar exemplar = new Exemplar();
		exemplar.setIdentificador(geraCodigo(exemplares));
		exemplares.add(exemplar);
		return exemplares;
	}
	
	private Integer geraCodigo(List<Exemplar> exemplares){
		Integer codigo = 1;
		for(Exemplar e : exemplares){
			if(e != null && e.getIdentificador().compareTo(codigo) >= 0){
				codigo = e.getIdentificador() + 1;
			}
		}
		return codigo;
	}
	
	@Transactional
	public void save(ObraBean bean){
		Obra obra = Obra.newInstance(bean);
		obraRepository.save(obra);
	}
	
	
	
	

}
