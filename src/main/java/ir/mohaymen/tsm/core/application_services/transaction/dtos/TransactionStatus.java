package ir.mohaymen.tsm.core.application_services.transaction.dtos;

import ir.mohaymen.tsm.core.domain_models.transaction.entities.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionStatus {
    private Status status;
    private String message;
}
