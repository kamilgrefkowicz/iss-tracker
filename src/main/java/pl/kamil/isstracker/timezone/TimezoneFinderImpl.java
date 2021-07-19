package pl.kamil.isstracker.timezone;

import net.iakovlev.timeshape.TimeZoneEngine;
import org.springframework.stereotype.Service;
import pl.kamil.isstracker.shared.dto.LocationData;

import java.time.ZoneId;
import java.util.Optional;

@Service
public class TimezoneFinderImpl implements TimezoneFinder {

    private final TimeZoneEngine timeZoneEngine = TimeZoneEngine.initialize();


    public ZoneId getZoneId(LocationData location) {
        Optional<ZoneId> zoneIdOptional = timeZoneEngine.query(location.getLatitude(), location.getLongitude());
        if (zoneIdOptional.isEmpty()) throw new RuntimeException();
        return zoneIdOptional.get();
    }
}

