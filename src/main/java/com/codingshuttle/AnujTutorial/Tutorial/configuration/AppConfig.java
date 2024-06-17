package com.codingshuttle.AnujTutorial.Tutorial.configuration;

import com.codingshuttle.AnujTutorial.Tutorial.DB;
import com.codingshuttle.AnujTutorial.Tutorial.DevDb;
import com.codingshuttle.AnujTutorial.Tutorial.ProdDB;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.management.modelmbean.ModelMBean;

@Configuration
public class AppConfig {

    @Bean
    @ConditionalOnProperty(name="project.mode" , havingValue = "development")
    public DB getDevDBBean(){
        return new DevDb();
    }

    @Bean
    @ConditionalOnProperty(name="project.mode" , havingValue = "production")
    public DB getProdDBDBBean(){
        return new ProdDB();
    }

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
