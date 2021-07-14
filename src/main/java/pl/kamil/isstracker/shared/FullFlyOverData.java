package pl.kamil.isstracker.shared;

import lombok.Data;

import java.time.Instant;
import java.time.ZonedDateTime;

@Data
public class FullFlyOverData {
    private float startAzimuth;
    private float maxAzimuth;
    private float maxElevation;
    private float endAzimuth;
    private int cloudPercentage;
    private ZonedDateTime start;
    private ZonedDateTime end;

    public FullFlyOverData(float startAzimuth, float maxAzimuth, float maxElevation, float endAzimuth) {
        this.startAzimuth = startAzimuth;
        this.maxAzimuth = maxAzimuth;
        this.maxElevation = maxElevation;
        this.endAzimuth = endAzimuth;
    }
}
