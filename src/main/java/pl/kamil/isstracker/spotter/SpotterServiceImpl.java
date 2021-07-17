package pl.kamil.isstracker.spotter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kamil.isstracker.iss.ISSLocator;
import pl.kamil.isstracker.map_marker.CalculateMarkerCommand;
import pl.kamil.isstracker.map_marker.Geocalculator;
import pl.kamil.isstracker.shared.dto.LocationData;
import pl.kamil.isstracker.shared.dto.FlyOver;
import pl.kamil.isstracker.shared.dto.CloudData;
import pl.kamil.isstracker.shared.dto.FullSpottingData;
import pl.kamil.isstracker.timezone.TimezoneFinder;
import pl.kamil.isstracker.weather.WeatherService;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpotterServiceImpl implements SpotterService {

    private final ISSLocator issLocator;
    private final WeatherService weatherService;
    private final TimezoneFinder timezoneFinder;
    private final Geocalculator geocalculator;


    @Override
    public List<FullSpottingData> findPossibleFlyOvers(LocationData locationData) {

        List<FlyOver> possibleFlyOvers = issLocator.findFlyOversForNextThreeDays(locationData);
        List<CloudData> cloudData = weatherService.getWeatherData(locationData);
        ZoneId zoneId = timezoneFinder.getZoneId(locationData);
        List<FullSpottingData> fullFlyoverDataSet = new ArrayList<>();

        aggregateData(possibleFlyOvers, cloudData, zoneId, fullFlyoverDataSet, locationData);

        return fullFlyoverDataSet;
    }

    private void aggregateData(List<FlyOver> possibleFlyOvers, List<CloudData> cloudData, ZoneId zoneId, List<FullSpottingData> fullFlyoverDataSet, LocationData locationData) {
        for (FlyOver flyover : possibleFlyOvers) {
            FullSpottingData data = convertToFull(flyover);
            setSpottingLocation(data, locationData);
            setFlyoverTimes(flyover, data, zoneId);
            setMapMarkers(data);

            Optional<CloudData> cloudDataOnFlyOver = getCloudData(cloudData, flyover);
            if (cloudDataOnFlyOver.isEmpty()) continue;
            data.setCloudPercentage(cloudDataOnFlyOver.get().getCloudPercentage());


            fullFlyoverDataSet.add(data);
        }
    }

    private void setMapMarkers(FullSpottingData data) {

        LocationData flyoverStart = geocalculator.getMarker(new CalculateMarkerCommand(data.getSpottingLocationLatitude(), data.getSpottingLocationLongitude(), data.getFlyoverStartAzimuth()));
        data.setStartMarkerLatitude(flyoverStart.getLatitude());
        data.setStartMarkerLongitude(flyoverStart.getLongitude());

        LocationData flyoverEnd = geocalculator.getMarker(new CalculateMarkerCommand(data.getSpottingLocationLatitude(), data.getSpottingLocationLongitude(), data.getFlyoverEndAzimuth()));
        data.setEndMarkerLatitude(flyoverEnd.getLatitude());
        data.setEndMarkerLongitude(flyoverEnd.getLongitude());
    }

    private void setSpottingLocation(FullSpottingData data, LocationData locationData) {
        data.setSpottingLocationLatitude(locationData.getLatitude());
        data.setSpottingLocationLongitude(locationData.getLongitude());
    }

    private void setFlyoverTimes(FlyOver flyover, FullSpottingData data, ZoneId zoneId) {
        data.setFlyoverStartTime(Instant.ofEpochSecond(flyover.getStartUtc()).atZone(zoneId));
        data.setFlyoverEndTime(Instant.ofEpochSecond(flyover.getEndUtc()).atZone(zoneId));
        }

    private Optional<CloudData> getCloudData(List<CloudData> cloudData, FlyOver flyover) {
        int flyoverStartUtc = flyover.getStartUtc();
        return cloudData.stream()
                .filter(cloud -> cloud.getTimeUTC() > flyoverStartUtc)
                .findFirst();
    }

    private FullSpottingData convertToFull(FlyOver flyover) {
        return new FullSpottingData(flyover.getStartAzimuth(), flyover.getMaxAzimuth(), flyover.getMaxElevation(), flyover.getEndAzimuth());
    }
}















