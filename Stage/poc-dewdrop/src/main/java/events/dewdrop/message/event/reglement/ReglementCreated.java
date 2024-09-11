package events.dewdrop.message.event.reglement;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class ReglementCreated extends ReglementEvent {

    private String recipientName;

    public ReglementCreated(UUID accountNumber, String recipientName ) {
        super(accountNumber);
        this.recipientName = recipientName;

    }
}