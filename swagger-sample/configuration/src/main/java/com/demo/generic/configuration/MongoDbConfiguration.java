package com.demo.generic.configuration;

import com.demo.generic.core.usecase.SiteCrud;
import com.demo.generic.swaggerSample.mongo.MongodbDataStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class MongoDbConfiguration {

    @Value("${mongodb.hostname}")
    private String mongoDbHostname;
    @Value("${mongodb.dbName}")
    private String mongoDbDatabaseName;
    @Value("${mongodb.collectionName}")
    private String mongoDbCollectionName;

    @Bean
    public SiteCrud createMongoDataStore() {
        return new MongodbDataStore(mongoDbHostname, mongoDbDatabaseName, mongoDbCollectionName);
    }

}
