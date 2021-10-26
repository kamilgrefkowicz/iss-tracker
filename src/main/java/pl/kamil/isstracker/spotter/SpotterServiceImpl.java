package pl.kamil.isstracker.spotter;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import pl.kamil.isstracker.iss.FlyOver;
import pl.kamil.isstracker.iss.ISSLocator;
import pl.kamil.isstracker.map_marker.CalculateMarkerCommand;
import pl.kamil.isstracker.map_marker.Geocalculator;
import pl.kamil.isstracker.shared.dto.LocationData;
import pl.kamil.isstracker.spotter.domain.FullSpottingData;
import pl.kamil.isstracker.spotter.domain.IssSpottingData;
import pl.kamil.isstracker.spotter.domain.PoorSpottingData;
import pl.kamil.isstracker.timezone.TimezoneFinder;
import pl.kamil.isstracker.weather.CloudData;
import pl.kamil.isstracker.weather.WeatherService;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpotterServiceImpl implements SpotterService {

    private final ISSLocator issLocator;
    private final WeatherService weatherService;
    private final TimezoneFinder timezoneFinder;
    private final Geocalculator geocalculator;
    private final SpottingDataRepository repository;


    @Override
    public List<PoorSpottingData> findPossibleFlyOvers(LocationData locationData) {

        List<IssSpottingData> generatedData = generateFlyovers(locationData);

//        repository.saveAll(generatedData);

        ZoneId zoneId = timezoneFinder.getZoneId(locationData);
        return mapToPoorSpottingData(generatedData, zoneId);
    }


    @SneakyThrows
    @Override
    public FullSpottingData getFullSpottingData(String id) {
        Optional<IssSpottingData> rawData = repository.findById(id);
        if (rawData.isEmpty()) throw new NotFoundException("Spotting data of requested id not found");

        FullSpottingData fullSpottingData = mapToFullSpottingData(rawData.get());

        setMapMarkers(fullSpottingData);
        setFlyoverTimes(fullSpottingData, rawData.get());

        return fullSpottingData;
    }

    private FullSpottingData mapToFullSpottingData(IssSpottingData issSpottingData) {
        return new FullSpottingData(issSpottingData.getId(), issSpottingData.getSpottingLocationLatitude(), issSpottingData.getSpottingLocationLongitude(), issSpottingData.getFlyoverStartAzimuth(), issSpottingData.getFlyoverMaxAzimuth(), issSpottingData.getFlyoverMaxElevation(), issSpottingData.getFlyoverEndAzimuth(), issSpottingData.getCloudPercentage());
    }

    private List<PoorSpottingData> mapToPoorSpottingData(List<IssSpottingData> spottingData, ZoneId zoneId) {

        return spottingData.stream()
                .map(data -> new PoorSpottingData(
                        Instant.ofEpochSecond(data.getStartUtc()).atZone(zoneId),
                        Instant.ofEpochSecond(data.getEndUtc()).atZone(zoneId),
                        data.getCloudPercentage(),
                        data.getFlyoverMaxElevation(),
                        data.getFlyoverStartAzimuth(),
                        data.getFlyoverEndAzimuth()))
                .collect(Collectors.toList());
    }

    private List<IssSpottingData> generateFlyovers(LocationData locationData) {
        CompletableFuture<List<CloudData>> cloudData = weatherService.getWeatherData(locationData);
        CompletableFuture<List<FlyOver>> possibleFlyOvers = issLocator.findFlyOversForNextThreeDays(locationData);

        try {
            return aggregateData(possibleFlyOvers.get(), cloudData.get(), locationData);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private List<IssSpottingData> aggregateData(List<FlyOver> possibleFlyOvers, List<CloudData> cloudData, LocationData locationData) {
        List<IssSpottingData> spottingData = new ArrayList<>();
        for (FlyOver flyover : possibleFlyOvers) {
            IssSpottingData issSpottingData = convertToIssSpottingData(flyover);
            setSpottingLocation(locationData, issSpottingData);

            Optional<CloudData> cloudDataOnFlyOver = getCloudData(cloudData, flyover);
            spottingData.add(issSpottingData);

            if (cloudDataOnFlyOver.isEmpty()) continue;
            issSpottingData.setCloudPercentage(cloudDataOnFlyOver.get().getCloudPercentage());

        }
        return spottingData;
    }

    private void setSpottingLocation(LocationData locationData, IssSpottingData data) {
        data.setSpottingLocationLatitude(locationData.getLatitude());
        data.setSpottingLocationLongitude(locationData.getLongitude());
    }

    private void setMapMarkers(FullSpottingData data) {
//
//        LocationData flyoverStart = geocalculator.getMarker(new CalculateMarkerCommand(data.getSpottingLocationLatitude(), data.getSpottingLocationLongitude(), data.getFlyoverStartAzimuth()));
//        data.setStartMarkerLatitude(flyoverStart.getLatitude());
//        data.setStartMarkerLongitude(flyoverStart.getLongitude());
//
//        LocationData flyoverEnd = geocalculator.getMarker(new CalculateMarkerCommand(data.getSpottingLocationLatitude(), data.getSpottingLocationLongitude(), data.getFlyoverEndAzimuth()));
//        data.setEndMarkerLatitude(flyoverEnd.getLatitude());
//        data.setEndMarkerLongitude(flyoverEnd.getLongitude());
    }


    private void setFlyoverTimes(FullSpottingData fullSpottingData, IssSpottingData rawData) {
        ZoneId zoneId = timezoneFinder.getZoneId(new LocationData(fullSpottingData.getSpottingLocationLatitude(), fullSpottingData.getSpottingLocationLongitude()));
        fullSpottingData.setFlyoverStartTime(Instant.ofEpochSecond(rawData.getStartUtc()).atZone(zoneId));
        fullSpottingData.setFlyoverEndTime(Instant.ofEpochSecond(rawData.getEndUtc()).atZone(zoneId));
    }

    private Optional<CloudData> getCloudData(List<CloudData> cloudData, FlyOver flyover) {
        int flyoverStartUtc = flyover.getStartUtc();
        return cloudData.stream()
                .filter(cloud -> cloud.getTimeUTC() > flyoverStartUtc)
                .findFirst();
    }

    private IssSpottingData convertToIssSpottingData(FlyOver flyover) {
        return new IssSpottingData(flyover.getStartAzimuth(), flyover.getMaxAzimuth(), flyover.getMaxElevation(), flyover.getEndAzimuth(), flyover.getStartUtc(), flyover.getEndUtc());
    }
}















