package com.alex.cloud.controller;

import com.alex.cloud.entity.AudioLibrary;
import com.alex.cloud.service.AggregationService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.Observer;

import java.util.HashMap;

@RestController
@Slf4j
public class AggregationController
{
    @Autowired
    private AggregationService service;

    @GetMapping("/aggregate/{id}")
    public DeferredResult<HashMap<String, AudioLibrary>> aggregate(@PathVariable Integer id)
    {
        Observable<HashMap<String, AudioLibrary>> result = aggregateObservable(id);

        return toDefferedResult(result);
    }

    private DeferredResult<HashMap<String, AudioLibrary>> toDefferedResult(Observable<HashMap<String, AudioLibrary>> details)
    {
        DeferredResult<HashMap<String, AudioLibrary>> result = new DeferredResult<>();
        //订阅
        details.subscribe(new Observer<HashMap<String, AudioLibrary>>()
        {
            @Override
            public void onCompleted()
            {
                log.info("完成");
            }

            @Override
            public void onError(Throwable e)
            {
                log.error("发生错误...", e);
            }

            @Override
            public void onNext(HashMap<String, AudioLibrary> stringAudioLibraryHashMap)
            {
                result.setResult(stringAudioLibraryHashMap);
            }
        });

        return result;
    }

    private Observable<HashMap<String, AudioLibrary>> aggregateObservable(Integer id)
    {
        //合并两个或多个Observables发出的数据项，根据指定的函数变换它们
        return Observable.zip(service.getAudioById(id), service.getMovieUserByUserId(id), (user, movieUser) ->
        {
            HashMap<String, AudioLibrary> map = Maps.newHashMap();
            map.put("user", user);
            map.put("movieUser", movieUser);

            return map;
        });
    }
}
