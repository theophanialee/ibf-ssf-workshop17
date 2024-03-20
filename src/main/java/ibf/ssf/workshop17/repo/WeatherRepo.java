package ibf.ssf.workshop17.repo;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ibf.ssf.workshop17.models.Weather;
import ibf.ssf.workshop17.utils.Util;
import jakarta.json.Json;

@Repository
public class WeatherRepo {
  

    @Autowired
    @Qualifier(Util.KEY_WEATHER)
    RedisTemplate<String,Weather> template;


    // save data and expire in 30 mins //30 seconds to test
    public void saveWeather(String city, Weather weather) {

        template.opsForList().rightPush(city, weather);
        template.expire(city,30,TimeUnit.MINUTES);

    }

    //get weather by city
    public Weather getWeatherByCity(String city){
        // Assuming you want to retrieve the first weather data entry for the city
        // You can adjust the index if you want to retrieve a different entry
        Weather weather = template.opsForList().index(city, 0);
        return weather;
    }

    // private String normalizeKey(String city) {
    //     return city.trim().toLowerCase().replaceAll("\\s+", "");
    // }

    // private String toJson(Weather weather){
    //     return Json.createObjectBuilder()
    //             .add("main", weather.getMain())
    //             . add("description", weather.getDescription())
    //             .add("icon", weather.getIcon())
    //             .build()
    //             .toString();
    // }
    
}