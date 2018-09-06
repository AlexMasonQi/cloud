package com.alex.cloud.feign;

import com.alex.cloud.entity.AudioLibrary;
import com.alex.cloud.util.DateTimeUtil;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FeignClientFallbackFactory implements FallbackFactory<AudioFeignClient>
{
    @Override
    public AudioFeignClient create(Throwable cause)
    {
        return id ->
        {
            AudioLibrary audioLibrary = new AudioLibrary();
            audioLibrary.setId(-1);
            audioLibrary.setAudioName("error message response");
            audioLibrary.setAudioTime("0");
            audioLibrary.setCreateUser("Alex");
            audioLibrary.setCreateTime(DateTimeUtil.getCurrentDateTime());
            log.info("fallback reason: " + cause);

            return audioLibrary;
        };
    }
}
