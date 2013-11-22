package br.com.biblioteca.app.bean;

import java.io.Serializable;
import java.util.List;

import org.primefaces.model.DualListModel;

import com.google.common.collect.Lists;

import br.com.biblioteca.app.login.seguranca.PerfilUsuario;
import br.com.biblioteca.app.login.seguranca.Permissao;

public class PerfilUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer version;
	private String nome;
	private List<Permissao> permissoes = Lists.newArrayList();
	private DualListModel<Permissao> permissoesDualList = new DualListModel<Permissao>();
	private Integer cotaEmprestimo;
	private Integer duracaoDoEmprestimo;
	
	public PerfilUsuarioBean(PerfilUsuario perfil, List<Permissao> permissoesDisponiveis) {
		this.id = perfil.getId();
		this.nome = perfil.getNome();
		this.permissoes = perfil.getPermissoes();
		this.version = perfil.getVersion();
		this.cotaEmprestimo = perfil.getCotaEmprestimo();
		this.duracaoDoEmprestimo = perfil.getDuracaoDoEmprestimo();
		permissoesDualList.setSource(permissoesDisponiveis);
		permissoesDualList.setTarget(this.permissoes);
	}
	
	public PerfilUsuarioBean(List<Permissao> permissoesDisponiveis){
		permissoesDualList.setSource(permissoesDisponiveis);
		permissoesDualList.setTarget(permissoes);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	
	public DualListModel<Permissao> getPermissoesDualList() {
		return permissoesDualList;
	}
	
	public void setPermissoesDualList(DualListModel<Permissao> permissoesDualList) {
		this.permissoesDualList = permissoesDualList;
	}
	
	public Integer getVersion() {
		return version;
	}

	public Integer getCotaEmprestimo() {
		return this.cotaEmprestimo;
	}
	
	public void setCotaEmprestimo(Integer cotaEmprestimo) {
		this.cotaEmprestimo = cotaEmprestimo;
	}

	public Integer getDuracaoDoEmprestimo() {
		return this.duracaoDoEmprestimo;
	}
	
	public void setDuracaoDoEmprestimo(Integer duracaoDoEmprestimo) {
		this.duracaoDoEmprestimo = duracaoDoEmprestimo;
	}

}
