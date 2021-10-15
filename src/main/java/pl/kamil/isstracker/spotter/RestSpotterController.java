package pl.kamil.isstracker.spotter;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kamil.isstracker.shared.dto.LocationData;
import pl.kamil.isstracker.spotter.domain.PoorSpottingData;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class RestSpotterController {

    private final SpotterService spotterService;

    @GetMapping("/get-flyovers")
    public ResponseEntity<List<PoorSpottingData>> getFlyovers(@Valid LocationData locationData, BindingResult bindingResult) {

        List<PoorSpottingData> possibleFlyOvers = spotterService.findPossibleFlyOvers(locationData);

        return ResponseEntity.ok(possibleFlyOvers);

    }
}
