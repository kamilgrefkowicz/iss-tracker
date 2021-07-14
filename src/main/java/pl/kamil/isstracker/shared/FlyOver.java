package pl.kamil.isstracker.shared;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlyOver {
    private float startAzimuth;
    private float maxAzimuth;
    private float maxElevation;
    private float endAzimuth;
    private int startUtc;
    private int endUtc;

    public FlyOver(JsonNode pass) {
        this.startAzimuth = pass.get("startAz").floatValue();
        this.maxAzimuth = pass.get("maxAz").floatValue();
        this.maxElevation = pass.get("maxEl").floatValue();
        this.endAzimuth = pass.get("endAz").floatValue();
        this.startUtc = pass.get("startUTC").asInt();
        this.endUtc = pass.get("endUTC").asInt();
    }


}
