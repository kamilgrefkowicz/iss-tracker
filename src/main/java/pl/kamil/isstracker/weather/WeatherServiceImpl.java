package pl.kamil.isstracker.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.kamil.isstracker.shared.dto.LocationData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    @Value("${app.api.openweatherapp.key}")
    private String key;

    private static final String OPEN_WEATHER_MAP_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast?lat={lat}&lon={lon}&appid={API key}";

    @Override
    public List<CloudData> getWeatherData(LocationData locationData) {

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> parameters = getPathParameters(locationData);

        ResponseEntity<String> response
                = restTemplate.getForEntity(OPEN_WEATHER_MAP_BASE_URL, String.class, parameters);

        if (response.getStatusCode().isError()) throw new RestClientException("Weather service api is down");

        List<CloudData> cloudData = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root;
        try {
            root = objectMapper.readTree(response.getBody());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RestClientException("Something went wrong on the weather service api side");
        }
        root.get("list").forEach(timestamp -> cloudData.add(new CloudData(timestamp)));

        return cloudData;

    }

    private Map<String, String> getPathParameters(LocationData locationData) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("lat", String.valueOf(locationData.getLatitude()));
        parameters.put("lon", String.valueOf(locationData.getLongitude()));
        parameters.put("API key", key);
        return parameters;
    }
}
