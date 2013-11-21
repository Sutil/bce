package br.com.biblioteca.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.biblioteca.app.obra.Autor;
import br.com.biblioteca.repository.ListQueryDslPredicateExecutor;

public interface AutorRepository extends JpaRepository<Autor, Long>, ListQueryDslPredicateExecutor<Autor> {

}
