package com.example.wfootball.external.apifootball;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ApiFootballConfig {

    @Value("${apifootball.base-url}")
    private String baseUrl;

    @Value("${apifootball.api-key}")
    private String apiKey;

    @Value("${apifootball.host}")
    private String host;
}
