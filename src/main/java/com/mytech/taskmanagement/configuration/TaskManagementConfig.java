package com.mytech.taskmanagement.configuration;


import okhttp3.OkHttpClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TaskManagementConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Bean
    public OkHttpClient okHttpClient(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        return client;
    }

}
