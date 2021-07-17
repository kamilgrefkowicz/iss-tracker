package pl.kamil.isstracker.spotter;

import pl.kamil.isstracker.shared.dto.LocationData;
import pl.kamil.isstracker.shared.dto.FullSpottingData;

import java.util.List;

public interface SpotterService {

    List<FullSpottingData> findPossibleFlyOvers(LocationData locationData);
}
