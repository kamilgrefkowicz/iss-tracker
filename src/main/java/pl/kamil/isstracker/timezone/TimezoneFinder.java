package pl.kamil.isstracker.timezone;

import pl.kamil.isstracker.spotter.CurrentLocation;

public interface TimezoneFinder {

    String getZoneId(CurrentLocation location);
}
