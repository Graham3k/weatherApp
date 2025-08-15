package com.example.weatherApp.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "weather", url = "http://api.openweathermap.org")
public interface WeatherProxy {

    @GetMapping("/geo/1.0/direct")
    List<Map<String,Object>> getCoordinates(@RequestParam("q") String city,
                                            @RequestParam("appid") String appId,
                                            @RequestParam(value = "limit",required = false) int limit);

    @GetMapping("/data/2.5/weather")
    Map<String,Object> getWeather(@RequestParam("lat") double lat,
                                  @RequestParam("lon") double lon,
                                  @RequestParam("units") String units,
                                  @RequestParam("appid") String appId);

}
