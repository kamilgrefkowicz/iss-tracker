package pl.kamil.isstracker.shared;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CloudData {

    private int timeUTC;
    private int cloudPercentage;

    public CloudData(JsonNode timestamp) {
        timeUTC = timestamp.get("dt").asInt();
        cloudPercentage = timestamp.get("clouds").get("all").asInt();
    }
}
