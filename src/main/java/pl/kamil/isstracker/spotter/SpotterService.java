package pl.kamil.isstracker.spotter;

import pl.kamil.isstracker.shared.CurrentLocation;
import pl.kamil.isstracker.shared.FullFlyOverData;

import java.util.List;

public interface SpotterService {

    List<FullFlyOverData> findPossibleFlyOvers(CurrentLocation currentLocation);
}
