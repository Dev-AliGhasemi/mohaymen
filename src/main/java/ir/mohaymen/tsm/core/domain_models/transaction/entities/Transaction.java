package ir.mohaymen.tsm.core.domain_models.transaction.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ir.mohaymen.tsm.core.domain_models.transaction.event.StatusChanged;
import ir.mohaymen.tsm.core.domain_models.transaction.event.TransactionCreated;
import ir.mohaymen.tsm.framework.entities.BaseEntity;
import ir.mohaymen.tsm.framework.events.Event;
import ir.mohaymen.tsm.framework.exception.IllegalTransactionException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "transactions", indexes = {@Index(name = "idx_source_account_number", columnList = "source_account_number"),
        @Index(name = "idx_destination_account_number", columnList = "destination_account_number")})
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
    @JsonSerialize(using = JalaliDateSerializer.class)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate createdAt;
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
        } else if (event instanceof StatusChanged statusChanged)
            this.status = statusChanged.getStatus();
    }

    @Override
    protected void invariantValidation() {
        if (type == TransactionType.WITHDRAW && sourceAccountNumber == null)
            throw new IllegalTransactionException("Source account number must not be null");
        if (type == TransactionType.DEPOSIT && destinationAccountNumber == null)
            throw new IllegalTransactionException("Destination account number must not be null");
        if (type == TransactionType.TRANSFER && (sourceAccountNumber == null || destinationAccountNumber == null))
            throw new IllegalTransactionException("Source account number and destination account number must not be null");
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalTransactionException("Amount must not be null or negative or zero");
        if (type == null)
            throw new IllegalTransactionException("Transaction type must not be null");
    }

    public void changeStatus(Status status) {
        handleEvent(new StatusChanged(id, status));
    }

    public void writeMessage(String message) {
        this.message = message;
    }
}
