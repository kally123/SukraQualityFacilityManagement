package com.sukra.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class DataSourceBean {
	Properties prop = null;

	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	@Primary
	public DataSource getDataSource() {

		prop = new Properties();
		try {

			File jarPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
			System.out.println("jarPath.getParent() : " + jarPath.getParent());
			String propertiesPath = jarPath.getPath().contains("jar")
					? jarPath.getParentFile().getParentFile().getParent()
					: jarPath.getParentFile().getParentFile().getAbsolutePath();
			propertiesPath = propertiesPath.replace("file:", "");
			System.out.println("propertiesPath :" + propertiesPath);
			prop.load(new FileInputStream(propertiesPath + "/app.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("URL :" + prop.getProperty("spring.datasource.url") + "/"
				+ prop.getProperty("spring.datasource.username") + "/"
				+ prop.getProperty("spring.datasource.password"));
		DataSource dataSource = DataSourceBuilder.create().url(prop.getProperty("spring.datasource.url"))
				.username(prop.getProperty("spring.datasource.username"))
				.password(prop.getProperty("spring.datasource.password")).driverClassName("com.mysql.jdbc.Driver")
				.build();
		System.out.println("Datasource CREATED...");
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan(new String[] { "com.sukra" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);

		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", prop.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		em.setJpaProperties(properties);

		return em;
	}
}
