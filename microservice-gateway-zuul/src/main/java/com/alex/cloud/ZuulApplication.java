package com.alex.cloud;

import com.alex.cloud.filter.PreRequestLogFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableZuulProxy
@ComponentScan(basePackages = "com.alex")
public class ZuulApplication
{
    @Bean
    public PreRequestLogFilter preRequestLogFilter()
    {
        return new PreRequestLogFilter();
    }

    public static void main(String[] args)
    {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
