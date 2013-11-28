package br.com.biblioteca.app.controller;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import br.com.biblioteca.app.bean.DevolucaoBean;
import br.com.biblioteca.app.bean.EmprestimoBean;
import br.com.biblioteca.app.login.seguranca.QUsuario;
import br.com.biblioteca.app.login.seguranca.Usuario;
import br.com.biblioteca.app.login.seguranca.repository.UsuarioRepository;
import br.com.biblioteca.app.model.Emprestimo;
import br.com.biblioteca.app.model.Exemplar;
import br.com.biblioteca.app.model.Multa;
import br.com.biblioteca.app.model.QEmprestimo;
import br.com.biblioteca.app.model.QExemplar;
import br.com.biblioteca.app.repository.EmprestimoRepository;
import br.com.biblioteca.app.repository.ExemplarRepository;
import br.com.biblioteca.app.repository.MultaRepository;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.mysema.query.BooleanBuilder;

@Controller
public class EmprestimoController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ExemplarRepository exemplarRepository;

	@Autowired
	private EmprestimoRepository emprestimoRepository;

	@Autowired
	private MultaRepository multaRepository;

	public List<Usuario> completeUsuario(String value) {
		List<Usuario> users = Lists.newArrayList();
		if (!Strings.isNullOrEmpty(value)) {
			BooleanBuilder builder = new BooleanBuilder();
			builder.and(QUsuario.usuario.login.containsIgnoreCase(value));
			users = usuarioRepository.findAll(builder);
		}
		return users;
	}

	public List<Exemplar> completeExemplar(String value) {
		List<Exemplar> exemplares = Lists.newArrayList();
		if (!Strings.isNullOrEmpty(value)) {
			BooleanBuilder builder = new BooleanBuilder();
			builder.and(QExemplar.exemplar.obra.titulo.containsIgnoreCase(value));
			exemplares = exemplarRepository.findAll(builder);
		}
		return exemplares;
	}

	public EmprestimoBean novo() {
		return new EmprestimoBean();
	}

	public List<Emprestimo> pesquisar() {
		return emprestimoRepository.findAll();
	}

	@Transactional
	public void gerarEmprestimo(EmprestimoBean bean) {
		try {
			validaCotaEmprestimo(bean.getUsuario());
			validaExemplarEmprestado(bean.getExemplar());
			Emprestimo emprestimo = Emprestimo.newInstance(bean);
			bean.getExemplar().emprestar();
			exemplarRepository.save(bean.getExemplar());
			emprestimoRepository.save(emprestimo);
			addMessageInfo("Empréstimo gerado com sucesso", "");
		} catch (Exception e) {
			e.printStackTrace();
			addMessageError("Erro ao gerar empréstimo", e.getMessage());
		}
	}

	private void validaCotaEmprestimo(Usuario user) {
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(QEmprestimo.emprestimo.usuario.eq(user));
		long count = emprestimoRepository.count(builder);
		checkArgument(count < user.getPerfil().getCotaEmprestimo(), "Usuário excedeu a cota de empréstimos");
	}

	private void validaExemplarEmprestado(Exemplar exemplar) {
		QEmprestimo emprestimo = QEmprestimo.emprestimo;
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(emprestimo.exemplar.eq(exemplar));
		builder.and(emprestimo.devolucao.isNull());
		long count = emprestimoRepository.count(builder);
		checkArgument(count == 0, "Exemplar já está emprestado.");
	}

	public DevolucaoBean devolver(Emprestimo emprestimo) {
		return DevolucaoBean.newInstance(emprestimo);
	}

	public void devolver(DevolucaoBean bean) {
		try {
			Multa multa = bean.getEmprestimo().devolver();
			if (multa != null) {
				multaRepository.save(multa);
			}
			emprestimoRepository.save(bean.getEmprestimo());
			addMessageInfo("Devolução gerada com sucesso!", "");
		} catch (Exception e) {
			addMessageError("Erro ao salvar", e.getMessage());
		}
	}

	private void addMessageError(String title, String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg));
	}

	private void addMessageInfo(String title, String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg));
	}
}
