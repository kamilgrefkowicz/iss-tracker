package pl.kamil.isstracker.spotter;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrentLocation {

    private BigDecimal latitude;
    private BigDecimal longitude;
    private BigDecimal altitude;
}
