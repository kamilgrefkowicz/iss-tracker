package pl.kamil.isstracker.spotter;

import java.time.LocalDateTime;

public interface SpotterService {

    void findNextVisibleFlyOver(CurrentLocation currentLocation);
}
