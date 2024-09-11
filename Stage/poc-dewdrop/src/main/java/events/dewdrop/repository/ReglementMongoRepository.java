package events.dewdrop.repository;

import events.dewdrop.entities.Reglement;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ReglementMongoRepository extends MongoRepository<Reglement, String> {
    @Aggregation("{ $group: { _id: null, maxReglementVersion: { $max: '$reglementVersion' } } }")
    Optional<Long> findMaxReglementVersion();
}

