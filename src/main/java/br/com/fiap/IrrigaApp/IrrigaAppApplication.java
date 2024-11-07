package br.com.fiap.IrrigaApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class IrrigaAppApplication {

	public static void main(String[] args) {
		// Log para verificar o valor da variável de ambiente
		System.out.println("Mongo URI from Environment: " + System.getenv("MONGO_URI"));

		SpringApplication.run(IrrigaAppApplication.class, args);
	}

}

