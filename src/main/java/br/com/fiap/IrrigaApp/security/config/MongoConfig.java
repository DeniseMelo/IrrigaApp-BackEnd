package br.com.fiap.IrrigaApp.security.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;


@Configuration
public class MongoConfig {

    @Bean
    @ConditionalOnExpression("!T(org.springframework.util.StringUtils).isEmpty('${MONGO_URI:}')")
    public MongoClient mongoClient() {
        String mongoUri = System.getenv("MONGO_URI");
        return MongoClients.create(mongoUri);
    }
}

