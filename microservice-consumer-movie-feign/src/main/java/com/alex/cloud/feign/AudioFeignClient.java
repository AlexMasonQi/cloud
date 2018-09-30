package com.alex.cloud.feign;

import com.alex.cloud.entity.AudioLibrary;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "microservice-provider-user")
public interface AudioFeignClient
{
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    AudioLibrary findAudioById(@PathVariable("id") Integer id);

    @RequestMapping(value = "/allAudio", method = RequestMethod.GET)
    List<AudioLibrary> findAllAudio();
}
