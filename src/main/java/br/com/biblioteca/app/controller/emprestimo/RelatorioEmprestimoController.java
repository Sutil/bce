package br.com.biblioteca.app.controller.emprestimo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mysema.query.types.Predicate;

import br.com.biblioteca.app.model.Emprestimo;
import br.com.biblioteca.app.repository.EmprestimoRepository;

@Controller
public class RelatorioEmprestimoController {
	
	@Autowired
	private EmprestimoRepository emprestimoRepository;
	
	
	public FiltroRelatorioEmprestimo newFiltro(){
		return new FiltroRelatorioEmprestimo();
	}
	
	public List<Emprestimo> pesquisar(FiltroRelatorioEmprestimo filtro){
		Predicate query = filtro.toQuery();
		return emprestimoRepository.findAll(query);
	}

}
