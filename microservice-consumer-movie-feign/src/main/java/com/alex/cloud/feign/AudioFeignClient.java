package com.alex.cloud.feign;

import com.alex.cloud.entity.AudioLibrary;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "microservice-provider-user")
public interface AudioFeignClient
{
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AudioLibrary findAudioById(@PathVariable("id") Integer id);
}
