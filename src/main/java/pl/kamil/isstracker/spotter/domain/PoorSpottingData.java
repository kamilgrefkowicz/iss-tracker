package pl.kamil.isstracker.spotter.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class PoorSpottingData {

    private ZonedDateTime startTime;
    private ZonedDateTime endTime;

    private int cloudPercentage;
    private double maxElevation;

    private double startAzimuth;
    private double endAzimuth;

}
