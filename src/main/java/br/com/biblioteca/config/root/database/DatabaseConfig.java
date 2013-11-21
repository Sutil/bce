package br.com.biblioteca.config.root.database;

import javax.sql.DataSource;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

public interface DatabaseConfig {

	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception;

	public DataSource dataSource() throws Exception;

}