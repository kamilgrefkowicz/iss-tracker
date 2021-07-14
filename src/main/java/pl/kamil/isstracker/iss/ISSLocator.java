package pl.kamil.isstracker.iss;

import pl.kamil.isstracker.shared.FlyOver;
import pl.kamil.isstracker.shared.CurrentLocation;

import java.util.List;

public interface ISSLocator {
    List<FlyOver> findFlyOversForNextThreeDays(CurrentLocation currentLocation) ;
}
