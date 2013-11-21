package br.com.biblioteca.app.login.seguranca;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.biblioteca.app.bean.UsuarioBean;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.common.collect.Lists;

@Entity
public class Usuario implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Version
	Integer version;

	@NotEmpty
	@Column(length = 30)
	private String login;

	@NotEmpty
	@Column(length = 64)
	private String senha = "132";

	@ManyToOne
	@JoinColumn(name = "perfil_fk")
	private PerfilUsuario perfil;

	Usuario() {
	}

	private Usuario(String login) {
		this.login = login;
	}

	public static Usuario newUsuario() {
		return new Usuario();
	}

	public static Usuario newUsuario(String login) {
		checkArgument(!Strings.isNullOrEmpty(login));
		return new Usuario(login);
	}
	
	private Usuario(UsuarioBean b){
		this.id = b.getId();
		this.login  = b.getLogin();
		this.senha = b.getSenha();
		this.version = b.getVersion();
		this.perfil = b.getPerfil();
	}
	
	public static Usuario newInstance(UsuarioBean bean){
		checkNotNull(bean, "Não é possível criar usuário passando um bean nulo");
		checkArgument(!Strings.isNullOrEmpty(bean.getLogin()), "Não é permitido LOGIN nulo ou vazio");
		checkArgument(!Strings.isNullOrEmpty(bean.getSenha()), "Não foi definida uma senha para o usuário");
		checkNotNull(bean.getPerfil(), "Defina um perfil para o usuário.");
		return new Usuario(bean);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario other = (Usuario) obj;
			return Objects.equal(this.login, other.login);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.login);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("login", login).toString();
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public PerfilUsuario getPerfil() {
		return perfil;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Builder<Permissao> builder = ImmutableSet.builder();
		builder.addAll(perfil.getPermissoes());
		return builder.build();
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Integer getVersion() {
		return version;
	}

}
