package pl.kamil.isstracker.spotter;

import pl.kamil.isstracker.shared.dto.LocationData;

import java.util.List;

public interface SpotterService {

    List<FullSpottingData> findPossibleFlyOvers(LocationData locationData);
}
