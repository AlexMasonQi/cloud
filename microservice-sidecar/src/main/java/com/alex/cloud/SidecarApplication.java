package com.alex.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableSidecar
public class SidecarApplication
{
    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate()
    {
        return builder.build();
    }

    public static void main(String[] args)
    {
        SpringApplication.run(SidecarApplication.class, args);
    }
}
