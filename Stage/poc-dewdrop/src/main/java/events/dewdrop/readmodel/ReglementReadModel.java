package events.dewdrop.readmodel;

import events.dewdrop.entities.Reglement;
import events.dewdrop.message.event.reglement.ReglementCreated;
import events.dewdrop.read.readmodel.annotation.EventHandler;
import events.dewdrop.read.readmodel.annotation.ReadModel;
import events.dewdrop.read.readmodel.annotation.Stream;
import events.dewdrop.read.readmodel.annotation.StreamStartPosition;
import javax.transaction.Transactional;

import events.dewdrop.repository.ReglementMongoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Log4j2
@Service
@ReadModel
@Stream(name = "ReglementAggregate")
public class ReglementReadModel {

    @Autowired
    ReglementMongoRepository reglementMongoRepository;

    @StreamStartPosition(name = "ReglementAggregate")
    public Long startPosition() {

        Optional<Long> maxIndemnisationVersion = reglementMongoRepository.findMaxReglementVersion();
        return  maxIndemnisationVersion.orElse(0L);
    }

    @EventHandler
    @Transactional
    public void on(ReglementCreated event) {
        log.info("Created:{}", event.getAccountNumber());

        Reglement reglement = new Reglement();
        reglement.on(event);
        reglementMongoRepository.save(reglement);
    }
}