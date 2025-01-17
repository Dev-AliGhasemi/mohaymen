package ir.mohaymen.tsm.core.domain_models.transaction.event;

import ir.mohaymen.tsm.core.domain_models.transaction.entities.TransactionType;
import ir.mohaymen.tsm.framework.events.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TransactionCreated implements Event {
    private Long sourceAccountNumber;
    private Long destinationAccountNumber;
    private BigDecimal amount;
    private TransactionType transactionType;
}
