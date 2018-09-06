package com.alex.cloud.api;

import com.alex.cloud.entity.AudioLibrary;
import com.alex.cloud.util.DateTimeUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServiceApi
{
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @HystrixCommand(fallbackMethod = "findByIdFallBack")
    @GetMapping("/audio/{id}")
    public AudioLibrary findAudioById(@PathVariable Integer id)
    {
        return restTemplate.getForObject("http://microservice-provider-user/" + id, AudioLibrary.class);
    }

    @GetMapping("/log-user-instance")
    public void logUserInstance()
    {
        ServiceInstance serviceInstance = loadBalancerClient.choose("microservice-provider-user");
        //打印当前选择的是哪个节点
        logger.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
    }

    public AudioLibrary findByIdFallBack(Integer id, Throwable throwable)
    {
        AudioLibrary audioLibrary = new AudioLibrary();
        audioLibrary.setId(-1);
        audioLibrary.setAudioName("error message response");
        audioLibrary.setAudioTime("0");
        audioLibrary.setCreateUser("Alex");
        audioLibrary.setCreateTime(DateTimeUtil.getCurrentDateTime());
        logger.error("Cannot connect port \"80\"", throwable);

        return audioLibrary;
    }
}
