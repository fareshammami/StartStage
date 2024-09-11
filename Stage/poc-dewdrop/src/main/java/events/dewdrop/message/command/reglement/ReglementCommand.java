package events.dewdrop.message.command.reglement;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import events.dewdrop.aggregate.annotation.AggregateId;
import events.dewdrop.structure.api.Command;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class ReglementCommand extends Command {
    @AggregateId
    protected UUID accountNumber;

    public ReglementCommand(UUID accountNumber) {
        this.accountNumber = accountNumber;
    }
}
