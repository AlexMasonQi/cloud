package com.alex.cloud.config;

import feign.Contract;
import feign.Logger;
import org.springframework.context.annotation.Bean;

public class FeignConfig
{
    @Bean
    public Contract feignContract()
    {
        return new feign.Contract.Default();
    }

    @Bean
    public Logger.Level feignLogger()
    {
        return Logger.Level.FULL;
    }
}
