package events.dewdrop.usecases.indemnisation.create;

import events.dewdrop.api.result.Result;
import events.dewdrop.api.validators.ValidationException;
import events.dewdrop.message.command.indemnisation.CreateIndemnisationCommand;
import events.dewdrop.repository.IndemnisationMongoRepository;
import events.dewdrop.Dewdrop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateIndemnisationUseCase {

    @Autowired
    private Dewdrop dewdrop;
    @Autowired
    private IndemnisationMongoRepository indemnisationMongoRepository;

    @Transactional
    public Result<Boolean> create(CreateIndemnisationCommand createIndemnisationCommand) throws ValidationException {

        return dewdrop.executeCommand(createIndemnisationCommand);
    }
}
