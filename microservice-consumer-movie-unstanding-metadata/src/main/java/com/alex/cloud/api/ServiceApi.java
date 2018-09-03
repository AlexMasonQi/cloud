package com.alex.cloud.api;

import com.alex.cloud.entity.AudioLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ServiceApi
{
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/audio/{id}")
    public AudioLibrary findAudioById(@PathVariable Integer id)
    {
        return restTemplate.getForObject("http://localhost:81/" + id, AudioLibrary.class);
    }

    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo()
    {
        return discoveryClient.getInstances("microservice-provider-user-metadata");
    }
}
