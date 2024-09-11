package events.dewdrop.entities;

import events.dewdrop.message.event.reglement.ReglementCreated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reglements")
public class Reglement {

    @Id
    private UUID accountNumber;
    private String recipientName;
    private Long reglementVersion;


    public void on(ReglementCreated event) {
       setAccountNumber(event.getAccountNumber());
       setRecipientName(event.getRecipientName());
       setReglementVersion(event.getVersion());

    }
}
