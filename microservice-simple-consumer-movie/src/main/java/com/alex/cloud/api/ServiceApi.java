package com.alex.cloud.api;

import com.alex.cloud.entity.AudioLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServiceApi
{
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/audio/{id}")
    public AudioLibrary findAudioById(@PathVariable Integer id)
    {
        return restTemplate.getForObject("http://localhost/" + id, AudioLibrary.class);
    }
}
