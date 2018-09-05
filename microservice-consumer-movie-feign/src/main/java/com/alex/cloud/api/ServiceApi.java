package com.alex.cloud.api;

import com.alex.cloud.entity.AudioLibrary;
import com.alex.cloud.feign.AudioFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceApi
{
    @Autowired
    private AudioFeignClient feignClient;

    @GetMapping("/audio/{id}")
    public AudioLibrary findAudioById(@PathVariable Integer id)
    {
        return feignClient.findAudioById(id);
    }
}
