package com.alex.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CustomizeFeign
{
    public static void main(String[] args)
    {
        SpringApplication.run(CustomizeFeign.class, args);
    }
}
