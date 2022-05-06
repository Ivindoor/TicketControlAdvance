package com.sanmina.tk.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.sanmina.tk.*" })
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration {
	@Autowired
	public Environment environment;

	@Bean(name = "SQLTicket")
	public LocalSessionFactoryBean sessionFactoryTicket() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSourceTicket());
		sessionFactory.setPackagesToScan(new String[] { "com.sanmina.tk.orm.model" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	public DataSource dataSourceTicket() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("ticket.jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("ticket.jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("ticket.jdbc.password"));
		return dataSource;
	}

	@Bean(name = "txsManagerTicket")
	@Autowired
	public HibernateTransactionManager transactionManagerTicket(@Qualifier(value = "SQLTicket") SessionFactory s) {
		HibernateTransactionManager txManagerTicket = new HibernateTransactionManager();
		txManagerTicket.setSessionFactory(s);
		return txManagerTicket;
	}

	@Bean(name = "SQLSkid")
	public LocalSessionFactoryBean sessionFactorySkid() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSourceSkid());
		sessionFactory.setPackagesToScan(new String[] { "com.sanmina.tk.orm.model.skid" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	public DataSource dataSourceSkid() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("skid.jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("skid.jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("skid.jdbc.password"));
		return dataSource;
	}

	@Bean(name = "txsManagerSkid")
	@Autowired
	public HibernateTransactionManager transactionManagerSkid(@Qualifier(value = "SQLSkid") SessionFactory s) {
		HibernateTransactionManager txManagerSkid = new HibernateTransactionManager();
		txManagerSkid.setSessionFactory(s);
		return txManagerSkid;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		return properties;
	}
}
