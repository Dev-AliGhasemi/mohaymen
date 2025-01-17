package ir.mohaymen.tsm.core.application_services.account.services;

import ir.mohaymen.tsm.core.domain_models.account.entities.Account;
import ir.mohaymen.tsm.core.domain_models.account.value_objects.IdentificationCode;
import ir.mohaymen.tsm.core.domain_services.account.repositories.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountQueryService {

    private final AccountRepository accountRepository;
    public AccountQueryService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Long getAccountNumber(String identificationCode) {
        //can save in cache
        Account account = accountRepository.findByIdentificationCode(new IdentificationCode(identificationCode));
        if (account == null)
            throw new EntityNotFoundException("Account with identification code " + identificationCode + " not found");
        return account.getAccountNumber();
    }

    public Account getAccount(Long accountNumber) {
        //can save in cache
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null)
            throw new EntityNotFoundException("Account with account number " + accountNumber + " not found");
        return account;
    }

    public BigDecimal getAmount(Long accountNumber) {
        //at first check from cache
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null)
            throw new EntityNotFoundException("Account with account number " + accountNumber + " not found");
        return account.getAmount();
    }
}
