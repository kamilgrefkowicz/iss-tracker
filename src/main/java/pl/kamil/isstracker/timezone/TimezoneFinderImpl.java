package pl.kamil.isstracker.timezone;

import net.iakovlev.timeshape.TimeZoneEngine;
import org.springframework.stereotype.Service;
import pl.kamil.isstracker.shared.CurrentLocation;

import java.time.ZoneId;
import java.util.Optional;

@Service
public class TimezoneFinderImpl implements TimezoneFinder {

    private final TimeZoneEngine timeZoneEngine = TimeZoneEngine.initialize();


    public ZoneId getZoneId(CurrentLocation location) {
        Optional<ZoneId> zoneIdOptional = timeZoneEngine.query(location.getLatitude().doubleValue(), location.getLongitude().doubleValue());
        if (zoneIdOptional.isEmpty()) throw new RuntimeException();
        return zoneIdOptional.get();
    }
}

