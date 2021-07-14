package pl.kamil.isstracker.maps;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service("mapService")
public class GoogleMapService {

    @Value("${app.api.google.maps.street.view}")
    private String googleMapsApiKey;

    @RequestMapping
    public String getGoggleMapApiKey() {
        return googleMapsApiKey;
    }
}
