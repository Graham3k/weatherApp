package com.example.weatherApp.services;

import com.example.weatherApp.proxies.WeatherProxy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    private final WeatherProxy weatherProxy;

    public WeatherService(WeatherProxy weatherProxy)
    {
        this.weatherProxy = weatherProxy;
    }

    public List<Map<String, Object>> getCoordinates(String city, String apiKey)
    {
        return weatherProxy.getCoordinates(city,apiKey,1);

    }

    public Map<String,Object> getWeather(double lat, double lon, String apiKey)
    {
        return weatherProxy.getWeather(lat,lon,"metric",apiKey);
    }

}
