package pl.kamil.isstracker.spotter;

import pl.kamil.isstracker.shared.dto.LocationData;
import pl.kamil.isstracker.spotter.domain.FullSpottingData;
import pl.kamil.isstracker.spotter.domain.PoorSpottingData;

import java.util.List;

public interface SpotterService {

    List<PoorSpottingData> findPossibleFlyOvers(LocationData locationData);

    FullSpottingData getFullSpottingData(String id);
}
