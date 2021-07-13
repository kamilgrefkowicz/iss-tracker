package pl.kamil.isstracker.spotter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.kamil.isstracker.iss.ISSLocator;
import pl.kamil.isstracker.shared.FlyOver;
import pl.kamil.isstracker.weather.WeatherService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotterServiceImpl implements SpotterService {

    private final ISSLocator issLocator;
    private final WeatherService weatherService;


    @Override
    public void findNextVisibleFlyOver(CurrentLocation currentLocation) {
        List<FlyOver> possibleFlyOvers = issLocator.findFlyOversForNextThreeDays(currentLocation, LocalDateTime.now());
    }
}
