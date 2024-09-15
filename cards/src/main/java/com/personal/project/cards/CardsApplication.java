package com.personal.project.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*
//These are only required if we have not created these packages inside the main package
@ComponentScans({@ComponentScan("com.personal.project.accounts.controller")})
@EnableJpaRepositories("com.personal.project.accounts.repository")
@EntityScan("com.personal.project.accounts.entity")
*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImplCards") //passing the AuditAwareImpl bean
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API Documentation",
				description = "Cards microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Bickey Manandhar",
						email = "manandharbickey@gmail.com",
						url = "https://www.example.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.example.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Learn more here.",
				url = "https://github.com/BickeyManandhar/microservicePersonalProject"
		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
