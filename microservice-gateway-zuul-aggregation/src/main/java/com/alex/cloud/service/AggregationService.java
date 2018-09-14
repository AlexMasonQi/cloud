package com.alex.cloud.service;

import com.alex.cloud.entity.AudioLibrary;
import com.alex.cloud.util.DateTimeUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

@Service
public class AggregationService
{
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<AudioLibrary> getAudioById(Integer id)
    {
        //创建一个被观察者
        return Observable.create(observer ->
        {
            //请求用户微服务的/{id}端点
            AudioLibrary audioLibrary = restTemplate.getForObject("http://microservice-provider-user/{id}", AudioLibrary.class, id);
            observer.onNext(audioLibrary);
            observer.onCompleted();
        });
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<AudioLibrary> getMovieUserByUserId(Integer id)
    {
        return Observable.create(observer ->
        {
            //请求电影微服务的/audio/{id}端点
            AudioLibrary audioLibrary = restTemplate.getForObject("http://microservice-consumer-movie-ribbon/audio/{id}", AudioLibrary.class, id);
            observer.onNext(audioLibrary);
            observer.onCompleted();
        });
    }

    public AudioLibrary fallback(Integer id)
    {
        AudioLibrary audioLibrary = new AudioLibrary();
        audioLibrary.setId(-1);
        audioLibrary.setAudioName("error");
        audioLibrary.setCreateTime(DateTimeUtil.getCurrentDateTime());

        return audioLibrary;
    }
}
