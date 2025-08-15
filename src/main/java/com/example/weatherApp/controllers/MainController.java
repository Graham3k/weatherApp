package com.example.weatherApp.controllers;

import com.example.weatherApp.services.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class MainController {

    private final WeatherService weatherService;

    public MainController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public ResponseEntity<?> coordinates(@RequestParam String city)
    {

        try {
            String apiKey = Files.readString(Paths.get("src/main/resources/api_key")).trim();

            List<Map<String,Object>> coordsList = weatherService.getCoordinates(city,apiKey);

            if (coordsList.isEmpty())
            {
                return ResponseEntity.badRequest().body("City not found");
            }

            Map<String,Object> first = coordsList.getFirst();
            Double lat = (Double) first.get("lat");
            Double lon = (Double) first.get("lon");

            Map<String,Object> weatherData = weatherService.getWeather(lat,lon,apiKey);

            return ResponseEntity.ok(weatherData);

        } catch (Exception ex)
        {
            ex.printStackTrace();
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }



    }

}
