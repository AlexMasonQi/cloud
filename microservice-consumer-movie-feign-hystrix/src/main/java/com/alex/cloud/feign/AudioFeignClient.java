package com.alex.cloud.feign;

import com.alex.cloud.config.FeignConfig;
import com.alex.cloud.entity.AudioLibrary;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "microservice-provider-user", configuration = FeignConfig.class, fallbackFactory = FeignClientFallbackFactory.class)
@FunctionalInterface
public interface AudioFeignClient
{
    @RequestLine("GET /{id}")
    AudioLibrary findAudioById(@Param("id") Integer id);
}
