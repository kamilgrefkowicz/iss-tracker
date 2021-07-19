package pl.kamil.isstracker.spotter.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class FullSpottingData {

    private String id;

    private double spottingLocationLatitude;
    private double spottingLocationLongitude;

    private double flyoverStartAzimuth;
    private double flyoverMaxAzimuth;
    private double flyoverMaxElevation;
    private double flyoverEndAzimuth;
    private int cloudPercentage;
    private ZonedDateTime flyoverStartTime;
    private ZonedDateTime flyoverEndTime;
    private double startMarkerLatitude;
    private double startMarkerLongitude;
    private double endMarkerLatitude;
    private double endMarkerLongitude;


    public FullSpottingData(String id, double spottingLocationLatitude, double spottingLocationLongitude, double flyoverStartAzimuth, double flyoverMaxAzimuth, double flyoverMaxElevation, double flyoverEndAzimuth, int cloudPercentage) {

        this.id = id;
        this.spottingLocationLatitude = spottingLocationLatitude;
        this.spottingLocationLongitude = spottingLocationLongitude;
        this.flyoverStartAzimuth = flyoverStartAzimuth;
        this.flyoverMaxAzimuth = flyoverMaxAzimuth;
        this.flyoverMaxElevation = flyoverMaxElevation;
        this.flyoverEndAzimuth = flyoverEndAzimuth;
        this.cloudPercentage = cloudPercentage;
    }
}
