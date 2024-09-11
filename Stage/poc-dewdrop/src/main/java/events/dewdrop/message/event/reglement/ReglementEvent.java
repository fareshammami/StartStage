package events.dewdrop.message.event.reglement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import events.dewdrop.aggregate.annotation.AggregateId;
import events.dewdrop.structure.api.Event;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class ReglementEvent extends Event {
    @AggregateId
    protected UUID accountNumber;

    public ReglementEvent(UUID accountNumber) {
        this.accountNumber = accountNumber;
    }
}
