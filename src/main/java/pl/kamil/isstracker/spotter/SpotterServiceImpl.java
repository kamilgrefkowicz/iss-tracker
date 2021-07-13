package pl.kamil.isstracker.spotter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kamil.isstracker.iss.ISSLocator;
import pl.kamil.isstracker.shared.FlyOver;
import pl.kamil.isstracker.shared.CloudData;
import pl.kamil.isstracker.weather.WeatherService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpotterServiceImpl implements SpotterService {

    private final ISSLocator issLocator;
    private final WeatherService weatherService;


    @Override
    public List<FlyOver> findNextVisibleFlyOver(CurrentLocation currentLocation) {

        List<FlyOver> possibleFlyOvers = issLocator.findFlyOversForNextThreeDays(currentLocation);
        List<CloudData> cloudData = weatherService.getWeatherData(currentLocation);

        for (FlyOver flyover : possibleFlyOvers) {
            int flyoverStartUtc = flyover.getStartUtc();
            Optional<CloudData> cloudDataOnFlyOver = cloudData.stream()
                    .filter(cloud -> cloud.getTimeUTC() > flyoverStartUtc)
                    .findFirst();
            if (cloudDataOnFlyOver.isEmpty()) return possibleFlyOvers;
            flyover.setCloudPercentage(cloudDataOnFlyOver.get().getCloudPercentage());
        }
        return possibleFlyOvers;
    }
}
