package pl.kamil.isstracker.spotter.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class PoorSpottingData {

    private String flyoverUUID;
    private ZonedDateTime flyoverStartTime;
    private int cloudPercentage;
    private double flyoverMaxElevation;
}
