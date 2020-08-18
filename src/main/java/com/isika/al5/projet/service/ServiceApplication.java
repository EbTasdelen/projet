package com.isika.al5.projet.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}
	
	  @Bean
	    public ModelMapper getModelMapper() {
	        ModelMapper modelMapper = new ModelMapper();
	        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	        return modelMapper;
	    }
	  @Bean
	    public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() {
	        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
	        factory.setResources(new Resource[]{new ClassPathResource("projects.json")});
	        return factory;
	    }

}
