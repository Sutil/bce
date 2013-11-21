package br.com.biblioteca.repository;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

public interface ListQueryDslPredicateExecutor<T> extends QueryDslPredicateExecutor<T>{
	
	@Override
	public List<T> findAll(Predicate predcate);
	
	@Override
	public List<T> findAll(Predicate predcate, OrderSpecifier<?>... orders);

}
