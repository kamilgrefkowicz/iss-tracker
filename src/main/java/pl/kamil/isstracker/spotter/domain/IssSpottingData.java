package pl.kamil.isstracker.spotter.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class IssSpottingData {

    @Id
    private String id = UUID.randomUUID().toString();

    private double spottingLocationLatitude;
    private double spottingLocationLongitude;

    private double flyoverStartAzimuth;
    private double flyoverMaxAzimuth;
    private double flyoverMaxElevation;
    private double flyoverEndAzimuth;
    private int cloudPercentage;
    private int startUtc;
    private int endUtc;

    public IssSpottingData(double startAzimuth, double maxAzimuth, double maxElevation, double endAzimuth, int startUtc, int endUtc) {

        this.flyoverStartAzimuth = startAzimuth;
        this.flyoverMaxAzimuth = maxAzimuth;
        this.flyoverMaxElevation = maxElevation;
        this.flyoverEndAzimuth = endAzimuth;
        this.startUtc = startUtc;
        this.endUtc = endUtc;
    }
}
