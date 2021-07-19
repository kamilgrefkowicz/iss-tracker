package pl.kamil.isstracker.map_marker;

import com.grum.geocalc.Coordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;
import org.springframework.stereotype.Service;
import pl.kamil.isstracker.shared.dto.LocationData;

import java.math.BigDecimal;

@Service
public class GeocalculatorImpl implements Geocalculator {

    private static final float distanceToMarkerInMeters = 100;

    public LocationData getMarker(CalculateMarkerCommand command) {

        Coordinate centerLatitude = Coordinate.fromDegrees(command.getCenterPointLatitude());
        Coordinate centerLongitude = Coordinate.fromDegrees(command.getCenterPointLongitude());

        Point centerPoint = Point.at(centerLatitude, centerLongitude);

        Point marker = EarthCalc.gcd.pointAt(centerPoint, command.getAzimuth(),distanceToMarkerInMeters);

        return new LocationData(marker.latitude, marker.longitude);


    }
}
