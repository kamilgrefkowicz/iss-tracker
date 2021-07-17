package pl.kamil.isstracker.shared.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class FullSpottingData {
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


    public FullSpottingData(double flyoverStartAzimuth, double flyoverMaxAzimuth, double flyoverMaxElevation, double flyoverEndAzimuth) {
        this.flyoverStartAzimuth = flyoverStartAzimuth;
        this.flyoverMaxAzimuth = flyoverMaxAzimuth;
        this.flyoverMaxElevation = flyoverMaxElevation;
        this.flyoverEndAzimuth = flyoverEndAzimuth;
    }
}
