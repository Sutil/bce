package br.com.biblioteca.app.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.biblioteca.app.bean.ItemRelatorioReserva;
import br.com.biblioteca.app.model.Emprestimo;
import br.com.biblioteca.app.model.Obra;
import br.com.biblioteca.app.model.QEmprestimo;
import br.com.biblioteca.app.model.Reserva;
import br.com.biblioteca.app.repository.EmprestimoRepository;
import br.com.biblioteca.app.repository.ReservaRepository;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mysema.query.BooleanBuilder;

@Controller
public class RelatorioController {
	
	@Autowired
	private EmprestimoRepository emprestimoRepository;
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	public List<Obra> getEmprestadas(){
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(QEmprestimo.emprestimo.devolucao.isNull());
		List<Emprestimo> emprestimos = emprestimoRepository.findAll();
		Set<Obra> obras = Sets.newLinkedHashSet();
		for(Emprestimo e : emprestimos){
			obras.add(e.getExemplar().getObra());
		}
		return Lists.newLinkedList(obras);
	}
	
	public List<ItemRelatorioReserva> getReservadas(){
		List<Reserva> reservas = reservaRepository.findAll();
		List<ItemRelatorioReserva> itens = Lists.newArrayList();
		for(Reserva r : reservas){
			ItemRelatorioReserva item = new ItemRelatorioReserva(r.getObra(), 1);
			if(itens.contains(item)){
				for(ItemRelatorioReserva i : itens){
					if(item.equals(i)){
						i.addReservas();
						break;
					}
				}
			}
			else{
				itens.add(item);
			}
		}
		return itens;
	}
	
	public List<Emprestimo> getAtrasadas(){
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(QEmprestimo.emprestimo.devolucao.isNull());
		List<Emprestimo> emprestimos = emprestimoRepository.findAll(builder);
		List<Emprestimo> retorno = Lists.newArrayList();
		for(Emprestimo e : emprestimos){
			if(e.isAtrasado()){
				retorno.add(e);
			}
		}
		return retorno;
	}
	
	

}
