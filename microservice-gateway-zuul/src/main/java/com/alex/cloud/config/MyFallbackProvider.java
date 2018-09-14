package com.alex.cloud.config;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Component
public class MyFallbackProvider implements FallbackProvider
{
    @Override
    public String getRoute()
    {
        //*代表给所有微服务提供回退
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause)
    {
        if (cause instanceof HystrixTimeoutException)
        {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        }
        else
        {
            return this.fallbackResponse();
        }
    }

    private ClientHttpResponse fallbackResponse()
    {
        return this.response(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ClientHttpResponse response(final HttpStatus status)
    {
        return new ClientHttpResponse()
        {
            @Override
            public HttpStatus getStatusCode() throws IOException
            {
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException
            {
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException
            {
                return status.getReasonPhrase();
            }

            @Override
            public void close()
            {
            }

            @Override
            public InputStream getBody() throws IOException
            {
                return new ByteArrayInputStream("出现了一些微小的问题，请稍后重试".getBytes());
            }

            @Override
            public HttpHeaders getHeaders()
            {
                HttpHeaders headers = new HttpHeaders();
                MediaType type = new MediaType("application", "json", Charset.forName("UTF-8"));
                headers.setContentType(type);

                return headers;
            }
        };
    }
}
