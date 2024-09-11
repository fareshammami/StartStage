package events.dewdrop.entities;

import events.dewdrop.message.event.indemnisation.IndemnisationCreated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "indemnisations")
public class Indemnisation {

    @Id
    private String ref;
    private BeneficiaryType type;
    private BigDecimal amount;
    private List<PaymentRecipientIndem> paymentRecipients;
    private Integer paymentDay;
    private StatusEnum status ;
    private Long indemnisationVersion;


    public void on(IndemnisationCreated event) {
        setStatus(event.getStatus());
        setRef(event.getRef().toString());
        setAmount(event.getAmount());
        setType(event.getType());
        setPaymentDay(event.getPaymentDay());
        setPaymentRecipients(event.getPaymentRecipients());
        setIndemnisationVersion(event.getVersion());
    }
}
