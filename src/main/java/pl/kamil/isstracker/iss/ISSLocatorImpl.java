package pl.kamil.isstracker.iss;

import lombok.RequiredArgsConstructor;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.kamil.isstracker.shared.FlyOver;
import pl.kamil.isstracker.spotter.CurrentLocation;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ISSLocatorImpl implements ISSLocator {

    @Value("${app.api.2nyo.key}")
    private String apiKey;

    private final String baseUrl = "https://api.n2yo.com/rest/v1/satellite/";

    @Override
    public List<FlyOver> findFlyOversForNextThreeDays(CurrentLocation currentLocation, LocalDateTime now) {

        RestTemplate restTemplate = new RestTemplate();


        ResponseEntity<String> response
                = restTemplate.getForEntity(baseUrl + "/visualpasses/{id}/{observer_lat}/{observer_lng}/{observer_alt}/{days}/{min_visibility}/&apiKey={api_key}", String.class, "25544", currentLocation.getLatitude().toString(), currentLocation.getLongitude().toString(), "0", "2", "300", apiKey);


        return null;
    }
}
