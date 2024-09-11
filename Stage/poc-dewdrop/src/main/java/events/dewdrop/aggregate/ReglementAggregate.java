package events.dewdrop.aggregate;

import events.dewdrop.aggregate.annotation.Aggregate;
import events.dewdrop.aggregate.annotation.AggregateId;
import events.dewdrop.api.validators.ValidationException;
import events.dewdrop.command.CommandHandler;
import events.dewdrop.message.command.reglement.CreateReglementCommand;
import events.dewdrop.message.event.reglement.ReglementCreated;
import events.dewdrop.read.readmodel.annotation.EventHandler;
import events.dewdrop.structure.api.Event;
import events.dewdrop.structure.api.validator.DewdropValidator;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.UUID;

@Aggregate
@Slf4j
public class ReglementAggregate {

    @AggregateId
    private UUID accountNumber;
    private String recipientName;

    @CommandHandler
    public List<Event> create(CreateReglementCommand command) throws ValidationException {
        DewdropValidator.validate(command);
        ReglementCreated reglementCreated = new ReglementCreated(command.getAccountNumber(),command.getRecipientName());
        return List.of(reglementCreated);
    }

    @EventHandler
    public void on(ReglementCreated reglementCreated) {
        this.recipientName = reglementCreated.getRecipientName();
        this.accountNumber = reglementCreated.getAccountNumber();

    }

}
