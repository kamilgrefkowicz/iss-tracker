package pl.kamil.isstracker.spotter;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpottingDataRepository extends MongoRepository<FullSpottingData, Long> {
}
