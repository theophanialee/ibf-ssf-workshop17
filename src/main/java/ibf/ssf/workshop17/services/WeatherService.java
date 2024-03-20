package ibf.ssf.workshop17.services;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf.ssf.workshop17.models.Weather;
import ibf.ssf.workshop17.repo.WeatherRepo;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class WeatherService {

@Autowired
WeatherRepo weatherRep;

@Value("${weather.apiKey}")
public String apiKey;

public String lon;
public String lat;

// public List<Weather> weatherList = new LinkedList<>();

//Construct URL to call


// to check if weather exits - GET by city then SAVE if does not exit
public Weather checkCache(String city) {
    Weather weather = weatherRep.getWeatherByCity(city);
    if (weather == null) {
        System.out.println("Weather data not found in cache for city: " + city);
        weather = currentWeatherByCoordinates(city);
        weatherRep.saveWeather(city, weather);
        System.out.println("Weather data saved to cache for city: " + city);
    } else {
        System.out.println("Weather data found in cache for city: " + city);
    }
    return weather;
}



// to add new weather
public Weather currentWeatherByCoordinates(String city){
    String main ;
    String description;
    String icon;
    String temp;
    String feelsLike;
    String tempMin;
    String tempMax;
    String pressure;
    String humidity;
    
    searchCoordinatesByCity(city);

        String SEARCH_URL2="https://api.openweathermap.org/data/2.5/weather";
    
        String url = UriComponentsBuilder
        .fromUriString(SEARCH_URL2)
        .queryParam("lon", lon)
        .queryParam("lat", lat)
        .queryParam("appid", apiKey)
        .toUriString();
    
    System.out.println("url: " + url);  
    
     // Make the GET request
     RequestEntity<Void> req = RequestEntity.get(url).build();
    
     // Make the call
     RestTemplate template = new RestTemplate();
     ResponseEntity<String> resp = template.exchange(req, String.class); 
    
    //   System.out.println(">>>>> Status code: " + resp.getStatusCode().value());
    //  System.out.println(">>>>> Payload: " + resp.getBody());

    JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
    JsonObject respData = reader.readObject();
    JsonObject weatherData = respData.getJsonArray("weather").getJsonObject(0);
    JsonObject mainData = respData.getJsonObject("main");

    main = weatherData.getString("main");
    description = weatherData.getString("description");
    icon = weatherData.getString("icon");

    temp = mainData.getJsonNumber("temp").toString();
    feelsLike = mainData.getJsonNumber("feels_like").toString();
    tempMin = mainData.getJsonNumber("temp_min").toString();
    tempMax = mainData.getJsonNumber("temp_max").toString();
    pressure = mainData.getJsonNumber("pressure").toString();
    humidity = mainData.getJsonNumber("humidity").toString();

    Weather weather = new Weather(city, icon, main, description, temp, feelsLike, tempMin, tempMax, pressure, humidity, lon, lat);
    // weatherList.add(weather);

    return weather;

}

public void searchCoordinatesByCity(String city) {
    String SEARCH_URL1="http://api.openweathermap.org/geo/1.0/direct";
 
     String url = UriComponentsBuilder
             .fromUriString(SEARCH_URL1)
             .queryParam("q", city.replaceAll(" ", "+"))
             .queryParam("appid", apiKey)
             .queryParam("limit", 5)
             .toUriString();
 
      System.out.println("url: " + url);  
 
     // Make the GET request
     RequestEntity<Void> req = RequestEntity.get(url).build();
 
     // Make the call
     RestTemplate template = new RestTemplate();
     ResponseEntity<String> resp = template.exchange(req, String.class); 
     
     // System.out.println(">>>>> Status code: " + resp.getStatusCode().value());
     // System.out.println(">>>>> Payload: " + resp.getBody());
 
     JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
     JsonArray respArr = reader.readArray();
     JsonObject firstElement = respArr.getJsonObject(0);
 
     lon = firstElement.getJsonNumber("lon").toString();
     lat = firstElement.getJsonNumber("lat").toString();
 
 // Now you have the values of lon and lat
 System.out.println("Longitude: " + lon);
 System.out.println("Latitude: " + lat);
 
 
 }
 
}
