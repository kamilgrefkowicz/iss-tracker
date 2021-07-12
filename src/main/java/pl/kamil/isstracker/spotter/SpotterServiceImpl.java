package pl.kamil.isstracker.spotter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.kamil.isstracker.iss.ISSLocator;
import pl.kamil.isstracker.weather.WeatherService;

@Service
@RequiredArgsConstructor
public class SpotterServiceImpl implements SpotterService {

    private final ISSLocator issLocator;
    private final WeatherService weatherService;


}
