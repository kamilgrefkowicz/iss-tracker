package pl.kamil.isstracker.map_marker;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.kamil.isstracker.shared.dto.LocationData;

@Data
@AllArgsConstructor
public class CalculateMarkerCommand {

    private LocationData centerPoint;
    private double azimuth;
}
