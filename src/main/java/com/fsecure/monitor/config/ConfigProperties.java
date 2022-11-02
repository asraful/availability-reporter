package com.fsecure.monitor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Component
@ConfigurationProperties("application.endpoints")
@PropertySource(value = "classpath:endpoints.yaml", factory = YamlPropertySourceFactory.class)
public class ConfigProperties {
    private List<Endpoint> endpoints = new ArrayList<>();

    public List<Endpoint> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<Endpoint> endpoints) {
        this.endpoints = endpoints;
    }
}
