package events.dewdrop.message.command.indemnisation;

import events.dewdrop.entities.BeneficiaryType;
import events.dewdrop.entities.PaymentRecipientIndem;
import events.dewdrop.entities.StatusEnum;
import lombok.*;

import java.util.List;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class CreateIndemnisationCommand extends IndemnisationtCommand {

    private BeneficiaryType type;
    private BigDecimal amount;
    private List<PaymentRecipientIndem> paymentRecipients;
    private Integer paymentDay;
    private StatusEnum status;

    public CreateIndemnisationCommand(UUID ref, BeneficiaryType type, BigDecimal amount, List<PaymentRecipientIndem> paymentRecipients, Integer paymentDay, StatusEnum status) {
        super(ref);
        this.type = type;
        this.amount = amount;
        this.paymentRecipients = paymentRecipients;
        this.paymentDay = paymentDay;
        this.status = status;

    }

    public CreateIndemnisationCommand(UUID uuid, String validated) {
    }
}
