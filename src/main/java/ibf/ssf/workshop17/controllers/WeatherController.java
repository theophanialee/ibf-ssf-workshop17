package ibf.ssf.workshop17.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ibf.ssf.workshop17.models.Weather;
import ibf.ssf.workshop17.services.HttpbinService;
import ibf.ssf.workshop17.services.WeatherService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/")

public class WeatherController {

    @Autowired
    WeatherService weatherService;
    @Autowired
    HttpbinService httpbinSvc;

    @GetMapping("/weather")
    public String getWeather(@RequestParam String city, Model model) {
      
        Weather weather = weatherService.checkCache(city);
        model.addAttribute("weather", weather);
        
        return "weather";
    }
    
    @GetMapping("/healthz")
    @ResponseBody
    public ResponseEntity<String> getHealthz() {
        JsonObject j = Json.createObjectBuilder()
        .build();

        if(httpbinSvc.isAlive()) {
            return ResponseEntity.ok(j.toString());
        }

        return ResponseEntity.status(400).body(j.toString());
    }
    

}
