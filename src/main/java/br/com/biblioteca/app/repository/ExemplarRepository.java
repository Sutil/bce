package br.com.biblioteca.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.biblioteca.app.obra.Exemplar;
import br.com.biblioteca.repository.ListQueryDslPredicateExecutor;

public interface ExemplarRepository extends JpaRepository<Exemplar, Long>, ListQueryDslPredicateExecutor<Exemplar> {

}
