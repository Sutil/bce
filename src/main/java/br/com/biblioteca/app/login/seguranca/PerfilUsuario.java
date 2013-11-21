package br.com.biblioteca.app.login.seguranca;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.biblioteca.app.bean.PerfilUsuarioBean;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

@Entity
public class PerfilUsuario implements Serializable{


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Version
    Integer version;

    @NotEmpty
    @Column(length = 60)
    private String nome;
    

    @ElementCollection(fetch = FetchType.EAGER, targetClass = Permissao.class)
    @Enumerated(EnumType.STRING)
    private List<Permissao> permissoes = Lists.newLinkedList();

    PerfilUsuario() {
    }

    private PerfilUsuario(String nome) {
            this.nome = nome;
    }

    public static PerfilUsuario newPerfilUsuario() {
            return new PerfilUsuario();
    }

    public static PerfilUsuario newPerfilUsuario(String nome) {
            checkArgument(!Strings.isNullOrEmpty(nome));
            return new PerfilUsuario(nome);
    }
    
    private PerfilUsuario(PerfilUsuarioBean bean){
    	this.id = bean.getId();
    	this.nome = bean.getNome();
    	this.permissoes = bean.getPermissoes();
    	this.version = bean.getVersion();
    }
    
    public static PerfilUsuario newInstance(PerfilUsuarioBean bean) {
		checkNotNull(bean);
		checkArgument(!Strings.isNullOrEmpty(bean.getNome()));
		checkNotNull(bean.getPermissoes());
		checkArgument(!bean.getPermissoes().isEmpty());
		return new PerfilUsuario(bean);
	}

    @Override
    public boolean equals(Object obj) {
            if (obj instanceof PerfilUsuario) {
                    PerfilUsuario other = (PerfilUsuario) obj;
                    return Objects.equal(this.nome, other.nome);
            }
            return false;
    }

    @Override
    public int hashCode() {
            return Objects.hashCode(this.nome);
    }

    @Override
    public String toString() {
            return Objects.toStringHelper(this).add("nome", this.nome).toString();
    }

    public PerfilUsuario addPermissao(Permissao permissao) {
            checkNotNull(permissao);
            if (!permissoes.contains(permissao)) {
                    permissoes.add(permissao);
            }
            return this;
    }

    public PerfilUsuario removePermissao(Permissao permissao) {
            checkNotNull(permissao);
            if (permissoes.contains(permissao)) {
                    permissoes.remove(permissao);
            }
            return this;
    }

    public Long getId() {
            return id;
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
    
    public Integer getVersion() {
		return version;
	}

	
}
