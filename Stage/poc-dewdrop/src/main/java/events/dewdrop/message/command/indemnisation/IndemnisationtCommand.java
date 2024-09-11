package events.dewdrop.message.command.indemnisation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import events.dewdrop.aggregate.annotation.AggregateId;
import events.dewdrop.structure.api.Command;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class IndemnisationtCommand extends Command {

    @AggregateId
    protected UUID ref;

    public IndemnisationtCommand(UUID ref) {
        this.ref = ref;
    }
}
