package com.mdud.spotifyfavorites.datasource;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Configuration
public class MongoConfig {

    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        EmbeddedMongoFactoryBean embeddedMongoFactoryBean = new EmbeddedMongoFactoryBean();
        embeddedMongoFactoryBean.setBindIp("localhost");

        MongoClient mongoClient = embeddedMongoFactoryBean.getObject();
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, "mongodb");
        return mongoTemplate;
    }
}
