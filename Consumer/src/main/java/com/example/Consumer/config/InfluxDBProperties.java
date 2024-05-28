package com.example.Consumer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.influx")
@Data
public class InfluxDBProperties {

    private String url;
    private char[] token;
    private String bucket;
    private String org;
}
