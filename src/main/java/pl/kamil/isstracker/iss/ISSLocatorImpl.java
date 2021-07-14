package pl.kamil.isstracker.iss;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.kamil.isstracker.shared.FlyOver;
import pl.kamil.isstracker.shared.CurrentLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class ISSLocatorImpl implements ISSLocator {

    @Value("${app.api.2nyo.key}")
    private String apiKey;

    private static final String N2YO_BASE_URL = "https://api.n2yo.com/rest/v1/satellite/visualpasses/{id}/{observer_lat}/{observer_lng}/{observer_alt}/{days}/{min_visibility}/&apiKey={api_key}";
    private static final String ISS_NORAD_ID = "25544";
    private static final String DAYS_SEARCHED = "3";
    private static final String MINIMUM_VISIBILITY_SECONDS = "300";

    @Override
    public List<FlyOver> findFlyOversForNextThreeDays(CurrentLocation currentLocation)   {

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> parameters = getPathParameters(currentLocation);

        ResponseEntity<String> response
                = restTemplate.getForEntity(N2YO_BASE_URL , String.class, parameters);

        if (response.getStatusCode().isError()) throw new RestClientException("ISS tracking api is down");

        return parseFlyOverData(response);
    }

    @NotNull
    private List<FlyOver> parseFlyOverData(ResponseEntity<String> response) {
        List<FlyOver> possFlyOvers = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root;
        try {
            root = objectMapper.readTree(response.getBody());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RestClientException("Something went wrong on ISS tracking api's side");
        }
        root.get("passes").forEach(pass -> possFlyOvers.add(new FlyOver(pass)));
        return possFlyOvers;
    }

    @NotNull
    private Map<String, String> getPathParameters(CurrentLocation currentLocation) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", ISS_NORAD_ID);
        parameters.put("observer_lat", currentLocation.getLatitude().toString());
        parameters.put("observer_lng", currentLocation.getLongitude().toString());
        parameters.put("observer_alt", "0");
        parameters.put("days", DAYS_SEARCHED);
        parameters.put("min_visibility", MINIMUM_VISIBILITY_SECONDS);
        parameters.put("api_key", apiKey);
        return parameters;
    }
}
