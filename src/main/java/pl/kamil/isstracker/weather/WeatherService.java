package pl.kamil.isstracker.weather;

import pl.kamil.isstracker.shared.CloudData;
import pl.kamil.isstracker.spotter.CurrentLocation;

import java.util.List;

public interface WeatherService {
    List<CloudData> getWeatherData(CurrentLocation currentLocation);
}
