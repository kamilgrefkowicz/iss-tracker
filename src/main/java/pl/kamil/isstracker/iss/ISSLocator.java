package pl.kamil.isstracker.iss;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.kamil.isstracker.shared.FlyOver;
import pl.kamil.isstracker.spotter.CurrentLocation;

import java.time.LocalDateTime;
import java.util.List;

public interface ISSLocator {
    List<FlyOver> findFlyOversForNextThreeDays(CurrentLocation currentLocation, LocalDateTime now) throws JsonProcessingException;
}
