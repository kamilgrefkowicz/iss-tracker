package pl.kamil.isstracker.timezone;

import pl.kamil.isstracker.shared.dto.LocationData;

import java.time.ZoneId;

public interface TimezoneFinder {

    ZoneId getZoneId(LocationData location);
}
