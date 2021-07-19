package pl.kamil.isstracker.weather;

import pl.kamil.isstracker.shared.dto.LocationData;

import java.util.List;

public interface WeatherService {
    List<CloudData> getWeatherData(LocationData locationData);
}
