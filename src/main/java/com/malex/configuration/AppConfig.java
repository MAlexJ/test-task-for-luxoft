package com.malex.configuration;


import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@ComponentScan(basePackages = "com.malex")
public class AppConfig {

    @Bean
    public PropertyPlaceholderConfigurer propertyConfigurer() {
        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();

        ClassPathResource dataSource = new ClassPathResource("db.properties");
        ClassPathResource hibernate = new ClassPathResource("hibernate.properties");
        ClassPathResource dbcp = new ClassPathResource("dbcp.properties");

        configurer.setLocations(dataSource, hibernate, dbcp);
        return configurer;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        resolver.setMaxUploadSize(20971520);
        resolver.setMaxInMemorySize(1048576);
        return resolver;
    }

    @Bean
    public Mapper beanMapper() {
        return new DozerBeanMapper();
    }

}
