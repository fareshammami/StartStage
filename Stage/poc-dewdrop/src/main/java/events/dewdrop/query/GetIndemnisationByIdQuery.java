package events.dewdrop.query;

import java.util.UUID;
import lombok.Data;

@Data
public class GetIndemnisationByIdQuery {
    private UUID ref;

    public GetIndemnisationByIdQuery(UUID ref) {
        this.ref = ref;
    }
}

