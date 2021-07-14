package pl.kamil.isstracker.timezone;

import pl.kamil.isstracker.spotter.CurrentLocation;

import java.time.ZoneId;

public interface TimezoneFinder {

    ZoneId getZoneId(CurrentLocation location);
}
