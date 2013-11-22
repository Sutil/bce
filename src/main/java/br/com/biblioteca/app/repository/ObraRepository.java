package br.com.biblioteca.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.biblioteca.app.model.Obra;
import br.com.biblioteca.repository.ListQueryDslPredicateExecutor;

public interface ObraRepository extends JpaRepository<Obra, Long>, ListQueryDslPredicateExecutor<Obra>{

}
