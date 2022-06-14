package com.web.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan(basePackages = {"com.web", "com.dto"})
@EnableJpaRepositories(basePackages = "com.web", transactionManagerRef = "transactionManager")
@EnableTransactionManagement
public class AnnotationWebConfig {

    @Bean(name = "entityManagerFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));
        sessionFactory.setPackagesToScan("com.model");
        return sessionFactory;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                sessionFactory().getObject());
        return transactionManager;
    }

//    @Bean
//    public JavaMailSenderImpl javaMailSenderImpl() {
//        return new JavaMailSenderImpl();
//    }
//
//    @Bean
//    public VelocityEngine velocityEngine() {
//        VelocityEngine velocityEngine = new VelocityEngine();
//        velocityEngine.addProperty("resource.loader", "class");
//        velocityEngine.addProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
//        velocityEngine.init();
//        return velocityEngine;
//    }
}
