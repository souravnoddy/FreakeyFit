package com.freaky.fit.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableCouchbaseRepositories
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Autowired private PropertyConfig propertyConfig;

    @Override
    public String getConnectionString() {
        return propertyConfig.getCouchbaseHost();
    }

    @Override
    public String getUserName() {
        return propertyConfig.getBucketname();
    }

    @Override
    public String getPassword() {
        return propertyConfig.getBucketPassword();
    }

    @Override
    public String getBucketName() {
        return propertyConfig.getBucketname();
    }
}
