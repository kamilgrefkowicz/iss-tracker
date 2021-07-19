package pl.kamil.isstracker.spotter;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class FullSpottingData {

    @Id
    private String id = UUID.randomUUID().toString();

    private double spottingLocationLatitude;
    private double spottingLocationLongitude;

    private double flyoverStartAzimuth;
    private double flyoverMaxAzimuth;
    private double flyoverMaxElevation;
    private double flyoverEndAzimuth;
    private int cloudPercentage;
//    private ZonedDateTime flyoverStartTime;
//    private ZonedDateTime flyoverEndTime;
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
