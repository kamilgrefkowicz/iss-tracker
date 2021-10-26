package pl.kamil.isstracker.spotter;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kamil.isstracker.map_marker.CalculateMarkerCommand;
import pl.kamil.isstracker.map_marker.Geocalculator;
import pl.kamil.isstracker.shared.dto.LocationData;
import pl.kamil.isstracker.spotter.domain.PoorSpottingData;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class RestSpotterController {

    private final SpotterService spotterService;
    private final Geocalculator geocalculator;

    @GetMapping("/get-flyovers")
    public ResponseEntity<List<PoorSpottingData>> getFlyovers(@Valid LocationData locationData, BindingResult bindingResult) {

        List<PoorSpottingData> possibleFlyOvers = spotterService.findPossibleFlyOvers(locationData);

        return ResponseEntity.ok(possibleFlyOvers);

    }

    @GetMapping("/get-markers")
    public ResponseEntity<LocationData[]> getMarkers(LocationData currentPosition, double startAzimuth, double endAzimuth) {

        LocationData startMarker = geocalculator.getMarker(new CalculateMarkerCommand(currentPosition, startAzimuth));
        LocationData endMarker = geocalculator.getMarker(new CalculateMarkerCommand(currentPosition, endAzimuth));

        LocationData[] result = new LocationData[]{startMarker, endMarker};

        return ResponseEntity.ok(result);
    }
}
