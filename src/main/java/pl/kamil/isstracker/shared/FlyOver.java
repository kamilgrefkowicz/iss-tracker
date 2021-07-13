package pl.kamil.isstracker.shared;

import lombok.Data;

@Data
public class FlyOver {
    private float startAzimuth;
    private float maxAzimuth;
    private float maxElevation;
    private float endAzimuth;
    private int startUtc;
    private int endUtc;
}
