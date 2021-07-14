package pl.kamil.isstracker.spotter;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import pl.kamil.isstracker.iss.ISSLocator;
import pl.kamil.isstracker.shared.FlyOver;
import pl.kamil.isstracker.shared.CloudData;
import pl.kamil.isstracker.shared.FullFlyOverData;
import pl.kamil.isstracker.timezone.TimezoneFinder;
import pl.kamil.isstracker.weather.WeatherService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpotterServiceImpl implements SpotterService {

    private final ISSLocator issLocator;
    private final WeatherService weatherService;
    private final TimezoneFinder timezoneFinder;


    @Override
    public List<FullFlyOverData> findPossibleFlyOvers(CurrentLocation currentLocation) {

        List<FlyOver> possibleFlyOvers = issLocator.findFlyOversForNextThreeDays(currentLocation);
        List<CloudData> cloudData = weatherService.getWeatherData(currentLocation);
        ZoneId zoneId = timezoneFinder.getZoneId(currentLocation);
        List<FullFlyOverData> fullFlyOverData = new ArrayList<>();

        for (FlyOver flyover : possibleFlyOvers) {
            FullFlyOverData data = convertToFull(flyover);

            Optional<CloudData> cloudDataOnFlyOver = getCloudData(cloudData, flyover);
            if (cloudDataOnFlyOver.isEmpty()) continue;

            data.setCloudPercentage(cloudDataOnFlyOver.get().getCloudPercentage());
            setFlyoverTimes(flyover, data, zoneId);

            fullFlyOverData.add(data);
        }
        return fullFlyOverData;
    }

    private void setFlyoverTimes(FlyOver flyover, FullFlyOverData data, ZoneId zoneId) {
        data.setStart(Instant.ofEpochSecond(flyover.getStartUtc()).atZone(zoneId));
        data.setEnd(Instant.ofEpochSecond(flyover.getEndUtc()).atZone(zoneId));
        }

    private Optional<CloudData> getCloudData(List<CloudData> cloudData, FlyOver flyover) {
        int flyoverStartUtc = flyover.getStartUtc();
        return cloudData.stream()
                .filter(cloud -> cloud.getTimeUTC() > flyoverStartUtc)
                .findFirst();
    }

    @NotNull
    private FullFlyOverData convertToFull(FlyOver flyover) {
        return new FullFlyOverData(flyover.getStartAzimuth(), flyover.getMaxAzimuth(), flyover.getMaxElevation(), flyover.getEndAzimuth());
    }
}















