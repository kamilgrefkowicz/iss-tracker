package pl.kamil.isstracker.iss;

import pl.kamil.isstracker.shared.dto.LocationData;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ISSLocator {
    CompletableFuture<List<FlyOver>> findFlyOversForNextThreeDays(LocationData locationData) ;
}
