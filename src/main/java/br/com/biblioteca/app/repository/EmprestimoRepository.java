package br.com.biblioteca.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.biblioteca.app.model.Emprestimo;
import br.com.biblioteca.repository.ListQueryDslPredicateExecutor;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>, ListQueryDslPredicateExecutor<Emprestimo> {

}
