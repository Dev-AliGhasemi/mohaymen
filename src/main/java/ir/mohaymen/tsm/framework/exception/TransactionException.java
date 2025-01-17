package ir.mohaymen.tsm.framework.exception;

import ir.mohaymen.tsm.core.domain_models.transaction.entities.Transaction;
import lombok.Getter;

@Getter
public class TransactionException extends RuntimeException {
    private Long transactionNumber;

    public TransactionException(Long transactionNumber, String message) {
        super(message);
        this.transactionNumber = transactionNumber;
    }
}
