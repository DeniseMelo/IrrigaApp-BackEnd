package br.com.fiap.IrrigaApp.security.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!ci")
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        String ciEnv = System.getenv("CI");
        if (ciEnv != null && ciEnv.equals("true")) {
            // Se estiver em CI/CD, retorne uma instância "mock" ou null para ignorar o MongoDB
            return null;
        }
        return MongoClients.create(System.getenv("MONGO_URI"));
    }
}
