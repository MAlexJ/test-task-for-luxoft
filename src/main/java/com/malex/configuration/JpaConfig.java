package com.malex.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.malex.repository")
public class JpaConfig {

    // DataSource
    @Value("${db.driver}")
    private String driver;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String userName;
    @Value("${db.password}")
    private String password;
    @Value("${db.entity.package}")
    private String packageEntity;

    // DBCP2
    @Value("${db.initialSize}")
    private Integer initialSize;
    @Value("${db.minIdle}")
    private Integer minIdle;
    @Value("${db.maxIdle}")
    private Integer maxIdle;
    @Value("${db.timeBetweenEvictionRunsMillis}")
    private Integer timeRuns;
    @Value("${db.minEvictableIdleTimeMillis}")
    private Integer minIdleTime;
    @Value("${db.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${db.validationQuery}")
    private String validationQuery;

    // Hibernate
    @Value("${data.showsql}")
    private boolean showSql;
    @Value("${data.format_sql}")
    private boolean formatSql;
    @Value("${data.use_sql_comments}")
    private boolean useSqlComments;
    @Value("${data.generateddl}")
    private boolean generateDdl;
    @Value("${data.dialect}")
    private String dialect;
    @Value("${data.hbm2ddl.auto}")
    private String hbm2ddlAuto;
    @Value("${data.database}")
    private String database;

    @Bean
    public DataSource dataSource() {
        // Base DataSource
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(userName);
        ds.setPassword(password);
        // DBCP
        ds.setInitialSize(initialSize);
        ds.setMinIdle(minIdle);
        ds.setMaxIdle(maxIdle);
        ds.setTimeBetweenEvictionRunsMillis(timeRuns);
        ds.setMinEvictableIdleTimeMillis(minIdleTime);
        ds.setTestOnBorrow(testOnBorrow);
        ds.setValidationQuery(validationQuery);
        return ds;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(generateDdl);
        jpaVendorAdapter.setDatabase(Database.valueOf(database));
        return jpaVendorAdapter;
    }

    private Properties jpaProperties() {
        // Config Hibernate
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        jpaProperties.setProperty("hibernate.dialect", dialect);
        jpaProperties.setProperty("hibernate.show_sql", String.valueOf(showSql));
        jpaProperties.setProperty("hibernate.format_sql", String.valueOf(formatSql));
        jpaProperties.setProperty("hibernate.use_sql_comments", String.valueOf(useSqlComments));
        return jpaProperties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        // DataSource
        entityManagerFactory.setDataSource(dataSource());
        // Provider JPA
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
        // Entity package
        entityManagerFactory.setPackagesToScan(packageEntity);
        // Config Hibernate
        entityManagerFactory.setJpaProperties(jpaProperties());
        return entityManagerFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        // Config TM
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        // Register LocalContainerEntityManagerFactoryBean
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

}