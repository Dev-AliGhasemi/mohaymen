package ir.mohaymen.tsm.core.domain_models.transaction.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.mohaymen.tsm.core.domain_models.transaction.entities.TransactionType;
import ir.mohaymen.tsm.framework.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Create implements Command {
    private Long sourceAccountNumber;
    private Long destinationAccountNumber;
    private BigDecimal amount;
    @JsonProperty("type")
    private TransactionType transactionType;
}
