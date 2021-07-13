package pl.kamil.isstracker.spotter;

import pl.kamil.isstracker.shared.FlyOver;

import java.util.List;

public interface SpotterService {

    List<FlyOver> findNextVisibleFlyOver(CurrentLocation currentLocation);
}
