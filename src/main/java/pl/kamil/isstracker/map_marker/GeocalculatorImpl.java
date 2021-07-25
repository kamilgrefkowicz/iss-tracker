package pl.kamil.isstracker.map_marker;

import com.grum.geocalc.Coordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;
import org.springframework.stereotype.Service;
import pl.kamil.isstracker.shared.dto.LocationData;

@Service
public class GeocalculatorImpl implements Geocalculator {

    private static final float DISTANCE_TO_MARKER_IN_METERS = 50;

    public LocationData getMarker(CalculateMarkerCommand command) {

        Coordinate centerLatitude = Coordinate.fromDegrees(command.getCenterPointLatitude());
        Coordinate centerLongitude = Coordinate.fromDegrees(command.getCenterPointLongitude());

        Point centerPoint = Point.at(centerLatitude, centerLongitude);

        Point marker = EarthCalc.gcd.pointAt(centerPoint, command.getAzimuth(), DISTANCE_TO_MARKER_IN_METERS);

        return new LocationData(marker.latitude, marker.longitude);


    }
}
