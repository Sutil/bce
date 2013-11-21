package br.com.biblioteca.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.biblioteca.app.login.seguranca.PerfilUsuario;
import br.com.biblioteca.repository.ListQueryDslPredicateExecutor;

public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Long>, ListQueryDslPredicateExecutor<PerfilUsuario> {

}
