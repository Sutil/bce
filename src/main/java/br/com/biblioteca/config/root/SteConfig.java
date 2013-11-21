package br.com.biblioteca.config.root;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.aspectj.EnableSpringConfigured;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableSpringConfigured
@ComponentScan(basePackages = { "br.com.biblioteca.app", "br.com.biblioteca.config.root" }, scopedProxy = ScopedProxyMode.TARGET_CLASS, excludeFilters = @Filter(Controller.class))
@Configuration
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@EnableJpaRepositories(basePackages = "br.com.biblioteca.app")
public class SteConfig {

	@Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public JdbcTemplate jdbcTemplate() {
            return new JdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
            return new JpaTransactionManager(entityManagerFactory);
    }

}