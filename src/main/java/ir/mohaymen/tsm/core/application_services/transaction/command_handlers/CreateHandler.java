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
import ir.mohaymen.tsm.framework.exception.TransactionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("transactionCreateHandler")
@PropertySource(name = "config.properties", value = {"classpath:config.properties"})
public class CreateHandler implements CommandHandler<Create, Long> {

    @Autowired
    CacheManager cacheManager;
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

        if (create.getDestinationAccountNumber() != null && create.getDestinationAccountNumber().equals(create.getSourceAccountNumber()))
            transactionFailed(transaction, "Source account and Destination account number are the same");

        if (create.getTransactionType() != TransactionType.DEPOSIT) {
            Account sourceAccount = getAccount(create.getSourceAccountNumber());
            if (sourceAccount == null)
                transactionFailed(transaction, "Source account does not exist");
            else if (sourceAccount.getAccountStatus() != AccountStatus.ACTIVE)
                transactionFailed(transaction, "Source account is not active");

            BigDecimal transactionAmount = transactionAmountCalculator.calculate(create.getAmount());
            if (transactionAmount.compareTo(sourceAccount.getAmount()) >= 0)
                transactionFailed(transaction, "Source account amount is not enough");

            changeSourceAccountAmount(create, sourceAccount, transactionAmount, transaction);

            if (create.getTransactionType() == TransactionType.TRANSFER) {
                Account destinationAccount = getAccount(create.getDestinationAccountNumber());
                if (destinationAccount == null)
                    transactionFailed(transaction, "Destination account does not exist");
                else if (destinationAccount.getAccountStatus() != AccountStatus.ACTIVE)
                    transactionFailed(transaction, "Destination account is not active");
                changeDestinationAccountAmount(create, transaction, destinationAccount);
            }

            //Karmozd
            Transaction transactionKarmozd = new Transaction(create.getSourceAccountNumber(), bankAccountNumber,
                    transactionAmountCalculator.calculateKarmozd(create.getAmount()), TransactionType.KARMOZD);
            transactionKarmozd.changeStatus(Status.SUCCESS);
            transactionRepository.save(transactionKarmozd);

        } else
            changeDestinationAccountAmount(create, transaction, getAccount(create.getSourceAccountNumber()));

        transaction.changeStatus(Status.SUCCESS);
        transactionRepository.save(transaction);
        return transaction.getId();
    }

    @CachePut(cacheNames = "accounts", key = "#sourceAccount.id")
    public Account changeSourceAccountAmount(Create create, Account sourceAccount, BigDecimal transactionAmount, Transaction transaction) {
        sourceAccount.changeAmount(sourceAccount.getAmount().subtract(transactionAmount));
        accountRepository.save(sourceAccount);
        return sourceAccount;
    }

    @Cacheable(cacheNames = "accounts",key = "#id")
    public Account getAccount(Long id) {
        return accountRepository.findByAccountNumber(id);
    }

    private void transactionFailed(Transaction transaction, String message) {
        transaction.writeMessage(message);
        transactionRepository.save(transaction);
        throw new TransactionFailedException(transaction.getId(), message);
    }

    @CachePut(cacheNames = "accounts", key = "#destinationAccount.id")
    public Account changeDestinationAccountAmount(Create create, Transaction transaction, Account destinationAccount) {
        destinationAccount.changeAmount(destinationAccount.getAmount().add(create.getAmount()));
        accountRepository.save(destinationAccount);
        return destinationAccount;
    }
}
