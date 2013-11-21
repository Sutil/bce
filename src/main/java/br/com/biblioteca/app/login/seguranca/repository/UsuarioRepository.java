package br.com.biblioteca.app.login.seguranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.biblioteca.app.login.seguranca.Usuario;
import br.com.biblioteca.repository.ListQueryDslPredicateExecutor;

public interface UsuarioRepository extends JpaRepository<Usuario , Long>, ListQueryDslPredicateExecutor<Usuario> {

}
