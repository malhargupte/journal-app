package com.guptem.journalApp.services;

import com.guptem.journalApp.api.response.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private static final String API_KEY = "9415db34929a73e1ea4d8ba9c58beb97";
    private final static String URL = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Cacheable(cacheNames = "weather-cache", key = "#city")
    public WeatherResponse getWeather(String city) {

        logger.info("Fetching weather for city: {}", city);
        String finalURL = URL.replace("CITY", city).replace("API_KEY", API_KEY);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalURL, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;

    }
}
