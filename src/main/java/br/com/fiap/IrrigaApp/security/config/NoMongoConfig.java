package br.com.fiap.IrrigaApp.security.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("ci")
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class})
public class NoMongoConfig {

}
