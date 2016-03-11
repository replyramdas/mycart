package org.wai.pi.mycart.web.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySource("classpath:jdbc.properties")
@EnableTransactionManagement
public class MCDataSourceConfig {

	@Autowired
	private DataSource dataSource;

	@Bean(name = "dataSource")
	public DataSource dataSource(@Value("${jdbc.driverClassName}") final String driverClassName,
			@Value("${jdbc.mycart_db_url}") final String url, @Value("${jdbc.username}") final String username,
			@Value("${jdbc.password}") final String password) {
		try {
			ComboPooledDataSource ds = new ComboPooledDataSource();
			ds.setDriverClass(driverClassName);
			ds.setJdbcUrl(url);
			ds.setUser(username);
			ds.setPassword(password);
			ds.setAcquireIncrement(5);
			ds.setIdleConnectionTestPeriod(60);
			ds.setMaxPoolSize(100);
			ds.setMaxStatements(50);
			ds.setMinPoolSize(10);
			return ds;
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}

	}

	@Bean
	@Autowired
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
		return em;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
