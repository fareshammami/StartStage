package events.dewdrop.usecases.indemnisation.validate;

import events.dewdrop.Dewdrop;
import events.dewdrop.api.result.Result;
import events.dewdrop.api.validators.ValidationException;
import events.dewdrop.message.command.indemnisation.ValidateIndemnisationCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ValidateIndemnisationUseCase {

    @Autowired
    private Dewdrop dewdrop;
    public Result<Boolean> validate(ValidateIndemnisationCommand validateIndemnisationCommand) throws ValidationException {
        return dewdrop.executeCommand(validateIndemnisationCommand);
    }
}
