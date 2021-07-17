package pl.kamil.isstracker.map_marker;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalculateMarkerCommand {

    private double centerPointLatitude;
    private double centerPointLongitude;
    private double azimuth;
}
