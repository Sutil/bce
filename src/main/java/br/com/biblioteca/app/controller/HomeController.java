package br.com.biblioteca.app.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import br.com.biblioteca.app.bean.FiltroPesquisa;
import br.com.biblioteca.app.login.seguranca.QUsuario;
import br.com.biblioteca.app.login.seguranca.Usuario;
import br.com.biblioteca.app.login.seguranca.repository.UsuarioRepository;
import br.com.biblioteca.app.model.Obra;
import br.com.biblioteca.app.model.Reserva;
import br.com.biblioteca.app.repository.ObraRepository;
import br.com.biblioteca.app.repository.ReservaRepository;

import com.google.common.collect.Lists;
import com.mysema.query.BooleanBuilder;

@Controller
public class HomeController {

	@Autowired
	private ObraRepository obraRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ReservaRepository reservaRepository;

	public String getUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	private Usuario getUsuario() {
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(QUsuario.usuario.login.eq(getUser()));
		return usuarioRepository.findOne(builder);
	}

	public FiltroPesquisa newFiltro() {
		return new FiltroPesquisa();
	}

	public List<Obra> pesquisar(FiltroPesquisa filtro) {
		List<Obra> resultado = Lists.newLinkedList();
		if (filtro.toPredicate() != null) {
			resultado = obraRepository.findAll(filtro.toPredicate());
		}
		if (resultado.isEmpty()) {
			addMessage(FacesMessage.SEVERITY_WARN, "Obras não encontradas", "Sua pesquisa não retornou nenhum resultado");
		}
		System.out.println(resultado);
		return resultado;
	}

	public Reserva reservar(Obra obra) {
		try {
			return Reserva.newInstance(getUsuario(), obra);
		} catch (NullPointerException e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao gerar reserva", e.getMessage());
		} catch (IllegalArgumentException ae) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao gerar reserva", ae.getMessage());
		}
		return null;

	}

	@Transactional
	public void reservar(Reserva reserva) {
		try {
			reservaRepository.save(reserva);
			addMessage(FacesMessage.SEVERITY_INFO, "Reserva gerada com sucesso.", "");
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao gerar reserva", e.getMessage());
		}
	}

	private void addMessage(FacesMessage.Severity severity, String title, String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, title, message));
	}

}
