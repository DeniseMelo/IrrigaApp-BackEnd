package br.com.fiap.IrrigaApp.security.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        System.out.println(System.getenv("MONGO_URI"));
        String mongoUri = System.getenv("MONGO_URI");
        if (mongoUri == null || mongoUri.isEmpty()) {
            throw new IllegalStateException("A variável de ambiente MONGO_URI não está definida");
        }
        return MongoClients.create(mongoUri);
    }
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "bd-irriga-dev");
    }
}
