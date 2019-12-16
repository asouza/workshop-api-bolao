package br.com.caelum.workshop.workshopapibolao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@EnableAsync
@SpringBootApplication
@EnableJpaRepositories(enableDefaultTransactions = false)
public class Boot {

	public static void main(String[] args) {
		SpringApplication.run(Boot.class, args);
	}
	
	@Bean
	public SimpleModule javaTimeSerializationModule() {
		return new JavaTimeModule();
	}

}
