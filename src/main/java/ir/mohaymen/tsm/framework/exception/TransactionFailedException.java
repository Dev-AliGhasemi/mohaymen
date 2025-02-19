package ir.mohaymen.tsm.framework.exception;

import lombok.Getter;

@Getter
public class TransactionFailedException extends IllegalTransactionException {
    private Long id;
    public TransactionFailedException(Long id, String message) {
        super(message);
        this.id = id;
    }
}
