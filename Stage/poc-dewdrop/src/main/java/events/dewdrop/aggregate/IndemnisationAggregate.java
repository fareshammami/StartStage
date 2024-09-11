package events.dewdrop.aggregate;

import events.dewdrop.aggregate.annotation.Aggregate;
import events.dewdrop.aggregate.annotation.AggregateId;
import events.dewdrop.api.validators.ValidationException;
import events.dewdrop.command.CommandHandler;
import events.dewdrop.entities.BeneficiaryType;
import events.dewdrop.entities.PaymentRecipientIndem;
import events.dewdrop.entities.StatusEnum;
import events.dewdrop.message.command.indemnisation.CreateIndemnisationCommand;
import events.dewdrop.message.command.indemnisation.ValidateIndemnisationCommand;
import events.dewdrop.message.event.indemnisation.IndemnisationCreated;
import events.dewdrop.message.event.indemnisation.IndemnisationValidated;
import events.dewdrop.read.readmodel.annotation.EventHandler;
import events.dewdrop.structure.api.Event;
import events.dewdrop.structure.api.validator.DewdropValidator;
import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;
import java.util.*;

@Aggregate
@Slf4j
public class IndemnisationAggregate {

    @AggregateId
    private UUID ref;
    private BeneficiaryType type;
    private BigDecimal amount;
    private List<PaymentRecipientIndem> paymentRecipients;
    private Integer paymentDay;
    private StatusEnum status ;


    @CommandHandler
    public List<Event> create(CreateIndemnisationCommand command) throws ValidationException {
        DewdropValidator.validate(command);
        IndemnisationCreated indemnisationCreated = new IndemnisationCreated(command.getRef(), command.getType(), command.getAmount(), command.getPaymentRecipients(), command.getPaymentDay(), command.getStatus());
        return List.of(indemnisationCreated);
    }

    @EventHandler
    public void on(IndemnisationCreated indemnisationCreated) {
        this.ref = indemnisationCreated.getRef();
        this.type = indemnisationCreated.getType();
        this.amount = indemnisationCreated.getAmount();
        this.paymentRecipients = indemnisationCreated.getPaymentRecipients();
        this.paymentDay = indemnisationCreated.getPaymentDay();
        this.status = indemnisationCreated.getStatus();
    }

    @CommandHandler
    public Event validate(ValidateIndemnisationCommand command) throws ValidationException {
            DewdropValidator.validate(command);
            IndemnisationValidated indemnisationValidated = new IndemnisationValidated(command.getRef(),command.getStatus());
            return indemnisationValidated;
    }

    @EventHandler
    public void on(IndemnisationValidated indemnisationValidated) {
       this.status = indemnisationValidated.getStatus();

    }
}