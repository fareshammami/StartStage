package events.dewdrop.message.command.reglement;

import lombok.*;
import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class CreateReglementCommand extends ReglementCommand {

    private String recipientName;
    public CreateReglementCommand(UUID accountNumber, String recipientName) {
        super(accountNumber);
        this.recipientName=recipientName;
    }

}
