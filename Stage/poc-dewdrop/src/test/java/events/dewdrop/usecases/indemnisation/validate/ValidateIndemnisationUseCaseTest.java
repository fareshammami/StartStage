package events.dewdrop.usecases.indemnisation.validate;

import static events.dewdrop.entities.StatusEnum.VALIDATED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import events.dewdrop.Dewdrop;
import events.dewdrop.api.result.Result;
import events.dewdrop.api.validators.ValidationException;
import events.dewdrop.message.command.indemnisation.CreateIndemnisationCommand;
import java.util.UUID;
import events.dewdrop.message.command.indemnisation.ValidateIndemnisationCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidateIndemnisationUseCaseTest {
    @Mock
    private Dewdrop dewdrop;
    @InjectMocks
    ValidateIndemnisationUseCase validateIndemnisationUseCase;

    @Test
    @DisplayName("create() - Given the needed parameters, when create() is called, then dewdrop.executeCommand() is called")
    void create() throws ValidationException {
        doReturn(Result.of(true)).when(dewdrop)
                .executeCommand(any(CreateIndemnisationCommand.class));

        ValidateIndemnisationCommand validateIndemnisationCommand = new ValidateIndemnisationCommand(UUID.randomUUID(), VALIDATED);
        Result<Boolean> result = validateIndemnisationUseCase.validate(validateIndemnisationCommand);
        assertThat(result.get(), is(true));
    }
}