package ir.mohaymen.tsm.core.domain_models.transaction.entities;

import ir.mohaymen.tsm.core.domain_models.transaction.event.StatusChanged;
import ir.mohaymen.tsm.core.domain_models.transaction.event.TransactionCreated;
import ir.mohaymen.tsm.framework.entities.BaseEntity;
import ir.mohaymen.tsm.framework.events.Event;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Date;

@Table(name = "transactions", schema = "tsm")
@Entity
@NoArgsConstructor
@Getter
public class Transaction extends BaseEntity<Transaction> {
    @Column(name = "source_account_number", length = 14, updatable = false)
    private Long sourceAccountNumber;
    @Column(name = "destination_account_number", length = 14, updatable = false)
    private Long destinationAccountNumber;
    @Column(name = "amount", nullable = false, updatable = false)
    private BigDecimal amount = new BigDecimal(0);
    @Column(name = "type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.FAILED;
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;
    @Column(name = "message")
    private String message;

    public Transaction(Long sourceAccountNumber, Long destinationAccountNumber, BigDecimal amount, TransactionType type) {
        handleEvent(new TransactionCreated(sourceAccountNumber, destinationAccountNumber, amount, type));
    }

    @Override
    protected void setStateByEvent(Event event) {
        if (event instanceof TransactionCreated transactionCreated) {
            this.sourceAccountNumber = transactionCreated.getSourceAccountNumber();
            this.destinationAccountNumber = transactionCreated.getDestinationAccountNumber();
            this.amount = transactionCreated.getAmount();
            this.type = transactionCreated.getTransactionType();
        }else if (event instanceof StatusChanged statusChanged)
            this.status = statusChanged.getStatus();
    }

    @Override
    protected void invariantValidation() {
        if (type == TransactionType.WITHDRAW && sourceAccountNumber == null)
            throw new IllegalArgumentException("Source account number must not be null");
        if (type == TransactionType.DEPOSIT && destinationAccountNumber == null)
            throw new IllegalArgumentException("Destination account number must not be null");
        if (type == TransactionType.TRANSFER && (sourceAccountNumber == null || destinationAccountNumber == null))
            throw new IllegalArgumentException("Source account number and destination account number must not be null");
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Amount must not be null or negative");
        if (type == null)
            throw new IllegalArgumentException("Transaction type must not be null");
    }

    public void changeStatus(Status status) {
        handleEvent(new StatusChanged(id,status));
    }

    public void writeMessage(String message) {
        this.message = message;
    }
}
