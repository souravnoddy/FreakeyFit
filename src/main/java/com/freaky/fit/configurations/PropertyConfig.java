package com.freaky.fit.configurations;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class PropertyConfig {

    @Value("${spring.couchbase.bucket.name}")
    private String bucketname;

    @Value("${spring.couchbase.bootstrap-hosts}")
    private String couchbaseHost;

    @Value("${spring.couchbase.bucket.password}")
    private String bucketPassword;


}
