package com.nfcaceres.transactionservice.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.eventbridge.AmazonEventBridge;
import com.amazonaws.services.eventbridge.AmazonEventBridgeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfig {
    @Value("${aws.access.key}")
    public String accessKey;

    @Value("${aws.secret.key}")
    public String secretKey;

    @Value("${aws.region}")
    private String region;

    @Bean
    public BasicAWSCredentials getAWSCredentials() {
        return new BasicAWSCredentials(accessKey, secretKey);
    }

    @Bean
    public AmazonEventBridge amazonEventBridge() {
        return AmazonEventBridgeClient.builder()
                .withCredentials(new AWSStaticCredentialsProvider(getAWSCredentials()))
                .withRegion(region)
                .build();
    }
}
