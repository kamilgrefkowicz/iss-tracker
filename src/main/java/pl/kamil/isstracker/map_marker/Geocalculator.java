package pl.kamil.isstracker.map_marker;

import pl.kamil.isstracker.shared.dto.LocationData;

public interface Geocalculator {

     LocationData getMarker(CalculateMarkerCommand command);
}
