package events.dewdrop.usecases.indemnisation.validate;

import events.dewdrop.api.result.Result;
import events.dewdrop.message.command.indemnisation.ValidateIndemnisationCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class ValidateIndemnisationController {

    @Autowired
    private ValidateIndemnisationUseCase validateIndemnisationUseCase;

    @PatchMapping("/indemnisation/validate")
    public String validate(@Valid @RequestBody ValidateIndemnisationCommand validateIndemnisationCommand) {
        try {

            Result<Boolean> isValidated = validateIndemnisationUseCase.validate(validateIndemnisationCommand);
            return "success";

        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
