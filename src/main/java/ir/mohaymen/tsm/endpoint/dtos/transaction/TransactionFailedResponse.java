package ir.mohaymen.tsm.endpoint.dtos.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionFailedResponse {
    private Long transactionNumber;
    private String message;
}
