package events.dewdrop.readmodel;

import events.dewdrop.Dewdrop;
import events.dewdrop.api.result.Result;
import events.dewdrop.api.validators.ValidationException;
import events.dewdrop.entities.Indemnisation;
import events.dewdrop.entities.PaymentRecipientIndem;
import events.dewdrop.message.command.reglement.CreateReglementCommand;
import events.dewdrop.message.event.indemnisation.IndemnisationCreated;
import events.dewdrop.message.event.indemnisation.IndemnisationValidated;
import events.dewdrop.query.GetIndemnisationByIdQuery;
import events.dewdrop.read.readmodel.annotation.EventHandler;
import events.dewdrop.read.readmodel.annotation.ReadModel;
import events.dewdrop.read.readmodel.annotation.Stream;
import events.dewdrop.read.readmodel.annotation.StreamStartPosition;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import events.dewdrop.read.readmodel.query.QueryHandler;
import events.dewdrop.repository.IndemnisationMongoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@ReadModel
@Stream(name = "IndemnisationAggregate")
public class IndemnisationReadModel {

    @Autowired
    private  IndemnisationMongoRepository indemnisationMongoRepository;
    @Autowired
    private Dewdrop dewdrop;

    @StreamStartPosition(name = "IndemnisationAggregate")
    public Long startPosition() {

        Optional<Long> maxIndemnisationVersion = indemnisationMongoRepository.findMaxIndemnisationVersion();
        return  maxIndemnisationVersion.orElse(0L);
    }

    @EventHandler
    @Transactional
    public void on(IndemnisationCreated event) {
        log.info("Created:{}", event.getRef());

        Indemnisation indemnisation = new Indemnisation();
        indemnisation.on(event);
        indemnisationMongoRepository.save(indemnisation);
    }

    @EventHandler
    @Transactional
    public void on(IndemnisationValidated event) throws ValidationException {
        log.info("validated :{}", event.getRef());
        Optional<Indemnisation> indemnisationOptional = indemnisationMongoRepository.findById(event.getRef().toString());
        if (indemnisationOptional.isPresent()) {

            List<PaymentRecipientIndem> paymentRecipients = indemnisationOptional.get().getPaymentRecipients();

            for (PaymentRecipientIndem recipient : paymentRecipients) {
                CreateReglementCommand createReglementCommand = new CreateReglementCommand(UUID.randomUUID(),recipient.getRecipientName());
                dewdrop.executeCommand(createReglementCommand);
            }

            indemnisationOptional.get().setStatus(event.getStatus());
            indemnisationMongoRepository.save(indemnisationOptional.get());
        }

    }
    @QueryHandler
    public Result<Indemnisation> findByAccountId(GetIndemnisationByIdQuery query) {
        Optional<Indemnisation> byAccountId = indemnisationMongoRepository.findById(String.valueOf(query.getRef()));
        log.info("findByAccountId:{}", byAccountId);
        return byAccountId.stream()
                .findFirst()
                .map(Result::of)
                .orElse(Result.empty());
    }
}
