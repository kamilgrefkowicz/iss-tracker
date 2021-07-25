package pl.kamil.isstracker.map_marker;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.kamil.isstracker.shared.dto.LocationData;

@RestController
@RequestMapping("/update")
@RequiredArgsConstructor
public class MarkerUpdateController {

    private final Geocalculator geocalculator;

    @GetMapping
    public LocationData getMarkerData(@RequestParam String centerPointLatitude, @RequestParam String centerPointLongitude, @RequestParam String azimuth) {

//        return geocalculator.getMarker(command);
        return new LocationData();
    }
}
