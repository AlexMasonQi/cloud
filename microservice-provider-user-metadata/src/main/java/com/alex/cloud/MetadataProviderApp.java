package com.alex.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.alex.cloud.dao")
@ComponentScan(basePackages = {"com.alex"})
public class MetadataProviderApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(MetadataProviderApp.class, args);
    }
}
