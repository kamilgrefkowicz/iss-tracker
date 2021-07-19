package pl.kamil.isstracker.spotter;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.kamil.isstracker.spotter.domain.FullSpottingData;
import pl.kamil.isstracker.spotter.domain.IssSpottingData;

public interface SpottingDataRepository extends MongoRepository<IssSpottingData, String> {
}
