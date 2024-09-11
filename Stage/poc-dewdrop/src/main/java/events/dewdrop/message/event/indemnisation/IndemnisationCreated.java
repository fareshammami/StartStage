package events.dewdrop.message.event.indemnisation;

import events.dewdrop.entities.BeneficiaryType;
import events.dewdrop.entities.PaymentRecipientIndem;
import events.dewdrop.entities.StatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class IndemnisationCreated extends IndemnisationEvent {

    private BeneficiaryType type;
    private BigDecimal amount;
    private List<PaymentRecipientIndem> paymentRecipients;
    private Integer paymentDay;
    private StatusEnum status ;

    public IndemnisationCreated(UUID ref, BeneficiaryType type, BigDecimal amount, List<PaymentRecipientIndem> paymentRecipients, Integer paymentDay, StatusEnum status) {
        super(ref);
        this.type = type;
        this.amount = amount;
        this.paymentRecipients = paymentRecipients;
        this.paymentDay = paymentDay;
        this.status = status;

    }
}
