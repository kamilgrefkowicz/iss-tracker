package pl.kamil.isstracker.shared;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrentLocation {

    private BigDecimal latitude;
    private BigDecimal longitude;
}
