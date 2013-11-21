package br.com.biblioteca.config.root.database;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import br.com.biblioteca.config.root.annotation.Producao;

@Producao
@Configuration
public class ProducaoDatabaseConfig implements DatabaseConfig {

	@Bean
	@Override
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPersistenceUnitName("mysql");
		return factoryBean;
	}

	@Bean
	@Override
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//		dataSource.setUrl("jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = sutil-scan.endereco.intranet)(PORT = 1521)) (FAILOVER = ON) (CONNECT_DATA = (SERVER = DEDICATED)(SERVICE_NAME = sutil)(FAILOVER_MODE = (TYPE = SELECT) (METHOD = BASIC) (RETRIES = 64) (DELAY = 4))))");
		dataSource.setUrl("jdbc:mysql://localhost:3306/start");
		dataSource.setUsername("root");
		dataSource.setPassword("Edu13546");
		dataSource.setTestOnBorrow(true);
		dataSource.setTestOnReturn(true);
		dataSource.setTestWhileIdle(true);
		dataSource.setTimeBetweenEvictionRunsMillis(1800000);
		dataSource.setNumTestsPerEvictionRun(3);
		dataSource.setMinEvictableIdleTimeMillis(1800000);
		return dataSource;
	}

}