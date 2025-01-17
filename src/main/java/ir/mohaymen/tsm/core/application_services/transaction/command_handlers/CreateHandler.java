package ir.mohaymen.tsm.core.application_services.transaction.command_handlers;

import ir.mohaymen.tsm.core.domain_models.account.entities.Account;
import ir.mohaymen.tsm.core.domain_models.account.entities.AccountStatus;
import ir.mohaymen.tsm.core.domain_models.transaction.commands.Create;
import ir.mohaymen.tsm.core.domain_models.transaction.entities.Status;
import ir.mohaymen.tsm.core.domain_models.transaction.entities.Transaction;
import ir.mohaymen.tsm.core.domain_models.transaction.entities.TransactionType;
import ir.mohaymen.tsm.core.domain_services.account.repositories.AccountRepository;
import ir.mohaymen.tsm.core.domain_services.transaction.repositories.TransactionRepository;
import ir.mohaymen.tsm.core.domain_services.transaction.services.TransactionAmountCalculator;
import ir.mohaymen.tsm.framework.application_services.CommandHandler;
import ir.mohaymen.tsm.framework.exception.TransactionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("transactionCreateHandler")
@PropertySource(name = "config.properties", value = {"classpath:config.properties"})
public class CreateHandler implements CommandHandler<Create, Long> {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionAmountCalculator transactionAmountCalculator;
    private final Long bankAccountNumber;

    public CreateHandler(TransactionRepository transactionRepository, AccountRepository accountRepository,
                         TransactionAmountCalculator transactionAmountCalculator,
                         @Value("${bank.account.number}") Long bankAccountNumber) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.transactionAmountCalculator = transactionAmountCalculator;
        this.bankAccountNumber = bankAccountNumber;
    }

    @Override
    public Long handle(Create create) {
        Transaction transaction = new Transaction(create.getSourceAccountNumber(), create.getDestinationAccountNumber(),
                create.getAmount(), create.getTransactionType());

        //TODO put below in Transactional
        if (create.getDestinationAccountNumber() != null && create.getDestinationAccountNumber().equals(create.getSourceAccountNumber()))
            transactionFailed(transaction, "Source account and Destination account number are the same");

        if (create.getTransactionType() != TransactionType.DEPOSIT) {

            Account sourceAccountNumber = accountRepository.findByAccountNumber(create.getSourceAccountNumber());
            if (sourceAccountNumber == null)
                transactionFailed(transaction, "Source account does not exist");
            else if (sourceAccountNumber.getAccountStatus() != AccountStatus.ACTIVE)
                transactionFailed(transaction, "Source account is not active");

            BigDecimal transactionAmount = transactionAmountCalculator.calculate(create.getAmount());
            if (transactionAmount.compareTo(sourceAccountNumber.getAmount()) > 0)
                transactionFailed(transaction, "Source account amount is not enough");

            sourceAccountNumber.changeAmount(sourceAccountNumber.getAmount().subtract(transactionAmount));

            if (create.getTransactionType() == TransactionType.TRANSFER) {
                changeDestinationAccountAmount(create, transaction);
            }
            accountRepository.save(sourceAccountNumber);

            //Karmozd
            Transaction transactionKarmozd = new Transaction(create.getSourceAccountNumber(), bankAccountNumber,
                    transactionAmountCalculator.calculateKarmozd(create.getAmount()), TransactionType.KARMOZD);
            transactionKarmozd.changeStatus(Status.SUCCESS);
            transactionRepository.save(transactionKarmozd);

        } else
            changeDestinationAccountAmount(create, transaction);

        transaction.changeStatus(Status.SUCCESS);
        transactionRepository.save(transaction);
        return transaction.getId();
    }

    private void transactionFailed(Transaction transaction, String message) {
        transaction.writeMessage(message);
        transactionRepository.save(transaction);
        throw new TransactionException(transaction.getId(), message);
    }

    private void changeDestinationAccountAmount(Create create, Transaction transaction) {
        Account destinationAccountNumber = accountRepository.findByAccountNumber(create.getDestinationAccountNumber());
        if (destinationAccountNumber == null)
            transactionFailed(transaction, "Destination account does not exist");
        else if (destinationAccountNumber.getAccountStatus() != AccountStatus.ACTIVE)
            transactionFailed(transaction, "Destination account is not active");

        destinationAccountNumber.changeAmount(destinationAccountNumber.getAmount().add(create.getAmount()));
        accountRepository.save(destinationAccountNumber);
    }
}
