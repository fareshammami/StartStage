package events.dewdrop.message.command.indemnisation;

import events.dewdrop.entities.StatusEnum;
import lombok.*;

import java.util.UUID;
@EqualsAndHashCode(callSuper = true)
@Getter
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ValidateIndemnisationCommand extends IndemnisationtCommand {

    private  StatusEnum status;
    public ValidateIndemnisationCommand(UUID ref,StatusEnum status) {
        super(ref);
        this.status=status;
    }

}
