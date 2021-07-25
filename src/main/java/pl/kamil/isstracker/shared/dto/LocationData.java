package pl.kamil.isstracker.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationData {

    private double latitude;
    private double longitude;
}
