package br.com.biblioteca.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.biblioteca.app.model.Reserva;
import br.com.biblioteca.repository.ListQueryDslPredicateExecutor;

public interface ReservaRepository extends JpaRepository<Reserva, Long>, ListQueryDslPredicateExecutor<Reserva>{

}
