package ir.mohaymen.tsm.core.application_services.transaction.services;

import ir.mohaymen.tsm.core.application_services.transaction.dtos.TransactionStatus;
import ir.mohaymen.tsm.core.domain_models.transaction.entities.Status;
import ir.mohaymen.tsm.core.domain_services.transaction.repositories.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class TransactionQueryService {

    private final TransactionRepository transactionRepository;
    public TransactionQueryService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionStatus getTransactionStatus(Long transactionNumber) {
        //at first check from cache
        AtomicReference<TransactionStatus> transactionStatusAtomicReference = new AtomicReference<>();
        transactionRepository.findById(transactionNumber).ifPresentOrElse(transaction -> {
            transactionStatusAtomicReference.set(new TransactionStatus(transaction.getStatus(),transaction.getMessage()));
        },() -> {
            throw new EntityNotFoundException("Transaction not found");
        });
        return transactionStatusAtomicReference.get();
    }
}
