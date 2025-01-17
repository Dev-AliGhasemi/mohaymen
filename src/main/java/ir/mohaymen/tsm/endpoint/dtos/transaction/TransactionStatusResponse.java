package ir.mohaymen.tsm.endpoint.dtos.transaction;

import ir.mohaymen.tsm.core.domain_models.transaction.entities.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionStatusResponse {
    private Status status;
    private String message;
}
