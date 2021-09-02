package pl.kamil.isstracker.weather;

import pl.kamil.isstracker.shared.dto.LocationData;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface WeatherService {
    CompletableFuture<List<CloudData>> getWeatherData(LocationData locationData);
}
