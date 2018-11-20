package pl.wojciechbury.organiser.models.services;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wojciechbury.organiser.models.Config;
import pl.wojciechbury.organiser.models.WeatherDto;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
public class WeatherService {

    final private Gson gson;

    @Autowired
    public WeatherService(Gson gson){
        this.gson = gson;
    }

    public WeatherDto loadWeatherFor(final String postalCode){

        WeatherDto weatherDto = convertJsonToCurrentWeather(readWebsite("https://api.openweathermap.org/data/2.5/weather?zip="
                + postalCode
                + "&appid="
                + Config.API_KEY));

        return weatherDto;
    }

    private WeatherDto convertJsonToCurrentWeather(String json){
        return gson.fromJson(json, WeatherDto.class);
    }

    private String readWebsite(String url){
        StringBuilder content = new StringBuilder();
        HttpURLConnection http;

        try{
            http = (HttpURLConnection) new URL(url).openConnection();
            http.setRequestMethod("GET");                                           //It is "GET" by default
            InputStream inputStream = http.getInputStream();

            int data;
            while((data = inputStream.read()) != -1){
                content.append((char) data);
            }

            inputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        return content.toString();
    }
}
