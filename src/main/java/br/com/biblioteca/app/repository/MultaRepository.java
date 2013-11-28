package br.com.biblioteca.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.biblioteca.app.model.Multa;
import br.com.biblioteca.repository.ListQueryDslPredicateExecutor;

public interface MultaRepository extends JpaRepository<Multa, Long>, ListQueryDslPredicateExecutor<Multa> {

}
