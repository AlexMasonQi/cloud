package com.alex.cloud.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServiceAPI
{
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public String findById()
    {
        return restTemplate.getForObject("http://microservice-sidecar-node-service/", String.class);
    }
}
