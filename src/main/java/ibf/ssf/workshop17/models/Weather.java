package ibf.ssf.workshop17.models;

import java.io.Serializable;

public class Weather implements Serializable {

    private String city;
    private String icon;
    private String main;
    private String description;

    private String temp;
    private String feelsLike;
    private String tempMin;
    private String tempMax;
    private String pressure;
    private String humidity;
    private String lon;
    private String lat;
    
    public Weather(String city, String icon, String main, String description, String temp, String feelsLike,
            String tempMin, String tempMax, String pressure, String humidity, String lon, String lat) {
        this.city = city;
        this.icon = icon;
        this.main = main;
        this.description = description;
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.pressure = pressure;
        this.humidity = humidity;
        this.lon = lon;
        this.lat = lat;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getMain() {
        return main;
    }
    public void setMain(String main) {
        this.main = main;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getTemp() {
        return temp;
    }
    public void setTemp(String temp) {
        this.temp = temp;
    }
    public String getFeelsLike() {
        return feelsLike;
    }
    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }
    public String getTempMin() {
        return tempMin;
    }
    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }
    public String getTempMax() {
        return tempMax;
    }
    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }
    public String getPressure() {
        return pressure;
    }
    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
    public String getHumidity() {
        return humidity;
    }
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
    public String getLon() {
        return lon;
    }
    public void setLon(String lon) {
        this.lon = lon;
    }
    public String getLat() {
        return lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    @Override
    public String toString() {
        return "Weather [city=" + city + ", icon=" + icon + ", main=" + main + ", description=" + description
                + ", temp=" + temp + ", feelsLike=" + feelsLike + ", tempMin=" + tempMin + ", tempMax=" + tempMax
                + ", pressure=" + pressure + ", humidity=" + humidity + ", lon=" + lon + ", lat=" + lat + "]";
    }
    
    

    
  
    
    
}
