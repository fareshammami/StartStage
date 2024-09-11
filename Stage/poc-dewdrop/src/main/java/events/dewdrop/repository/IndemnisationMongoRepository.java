package events.dewdrop.repository;

import events.dewdrop.entities.Indemnisation;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IndemnisationMongoRepository extends MongoRepository<Indemnisation, String> {
    @Aggregation("{ $group: { _id: null, maxIndemnisationVersion: { $max: '$indemnisationVersion' } } }")
    Optional<Long> findMaxIndemnisationVersion();
}

