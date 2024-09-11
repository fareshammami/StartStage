package events.dewdrop.usecases.indemnisation.create;

import events.dewdrop.api.result.Result;
import events.dewdrop.api.validators.ValidationException;
import events.dewdrop.message.command.indemnisation.CreateIndemnisationCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class CreateIndemnisationController {

    @Autowired
    private CreateIndemnisationUseCase createIndemnisationUseCase;

    @PatchMapping("/indemnisation/create")
    public ResponseEntity<?> create(@Valid @RequestBody CreateIndemnisationCommand createIndemnisationCommand) {
        try {
            Result<Boolean> ref = createIndemnisationUseCase.create(createIndemnisationCommand);

            URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/indemnisation/{ref}")
                    .buildAndExpand(ref)
                    .toUri();
            return ResponseEntity.created(location)
                    .header(String.valueOf(ref)).build();
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest()
                    .body("Validation failed: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create indemnisation: " + ex.getMessage());
        }
    }
}
