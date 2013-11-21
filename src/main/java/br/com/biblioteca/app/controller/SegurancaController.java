package br.com.biblioteca.app.controller;

import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import br.com.biblioteca.app.bean.PerfilUsuarioBean;
import br.com.biblioteca.app.bean.UsuarioBean;
import br.com.biblioteca.app.login.seguranca.PerfilUsuario;
import br.com.biblioteca.app.login.seguranca.Permissao;
import br.com.biblioteca.app.login.seguranca.Usuario;
import br.com.biblioteca.app.login.seguranca.repository.UsuarioRepository;
import br.com.biblioteca.app.repository.PerfilUsuarioRepository;

import com.google.common.collect.Lists;

@Controller
public class SegurancaController {

	@Autowired
	private PerfilUsuarioRepository perfilUsuarioRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<PerfilUsuario> getPerfis() {
		return perfilUsuarioRepository.findAll();
	}

	public PerfilUsuarioBean novoPerfil() {
		Collection<Permissao> values = Permissao.getValueMap().values();
		List<Permissao> permissoesDisponiveis = Lists.newArrayList();
		for (Permissao p : values) {
			permissoesDisponiveis.add(p);
		}
		return new PerfilUsuarioBean(permissoesDisponiveis);

	}

	public PerfilUsuarioBean edit(PerfilUsuario perfil) {
		List<Permissao> permissoesDisponiveis = Lists.newArrayList(Permissao.getValueMap().values());
		permissoesDisponiveis.removeAll(perfil.getPermissoes());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Editando perfil", "Editando perfil"));
		return new PerfilUsuarioBean(perfil, permissoesDisponiveis);
	}

	@Transactional
	public void salvarPerfil(PerfilUsuarioBean bean) {
		try {
			List<Permissao> permissoesVinculadas = bean.getPermissoesDualList().getTarget();
			bean.setPermissoes(permissoesVinculadas);
			PerfilUsuario perfil = PerfilUsuario.newInstance(bean);
			PerfilUsuario saved = perfilUsuarioRepository.saveAndFlush(perfil);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso ao salvar", "Perfil salvo no id " + saved.getId()));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar", e.getMessage()));
		}
	}

	public void deletePerfil(PerfilUsuario perfil) {
		try {
			perfilUsuarioRepository.delete(perfil);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "O perfil foi removido"));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir", e.getMessage()));
		}
	}
	
	public List<Usuario> getUsuarios(){
		return usuarioRepository.findAll();
	}

	public UsuarioBean novoUsuario() {
		return new UsuarioBean();
	}

	public UsuarioBean editarUsuario(Usuario user) {
		try {
			return UsuarioBean.newInstance(user);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Não foi possível preparar o Usuário para edição."));
		}
		return new UsuarioBean();
	}
	
	public void salvarUsuario(UsuarioBean bean){
		try{
			Usuario user = Usuario.newInstance(bean);
			Usuario saved = usuarioRepository.save(user);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Usuário salvo com id "+saved.getId()));
		}
		catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar", e.getMessage()));
		}
	}
	
	public void deletarUsuario(Usuario user){
		try{
			usuarioRepository.delete(user);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario removido!", ""));
		}
		catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao deletar", e.getMessage()));
		}
	}

}
