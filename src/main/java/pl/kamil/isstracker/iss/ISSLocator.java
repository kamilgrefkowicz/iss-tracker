package pl.kamil.isstracker.iss;

import pl.kamil.isstracker.shared.dto.LocationData;

import java.util.List;

public interface ISSLocator {
    List<FlyOver> findFlyOversForNextThreeDays(LocationData locationData) ;
}
