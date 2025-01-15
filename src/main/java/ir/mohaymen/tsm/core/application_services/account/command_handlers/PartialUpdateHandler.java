package ir.mohaymen.tsm.core.application_services.account.command_handlers;

import ir.mohaymen.tsm.core.domain_models.account.commands.PartialUpdate;
import ir.mohaymen.tsm.core.domain_models.account.entities.Account;
import ir.mohaymen.tsm.core.domain_services.account.repositories.AccountRepository;
import ir.mohaymen.tsm.core.domain_services.account.service.AccountMapping;
import ir.mohaymen.tsm.framework.application_services.CommandHandler;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class PartialUpdateHandler implements CommandHandler<PartialUpdate,Void> {

    private final AccountRepository accountRepository;

    public PartialUpdateHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Void handle(PartialUpdate partialUpdate) {
        Account account = accountRepository.findByAccountNumber(partialUpdate.getAccountNumber());
        if (account != null){
            AccountMapping.map(account,partialUpdate);
            accountRepository.save(account);
        }else
            throw new EntityNotFoundException("Account is not found.");

        return null;
    }
}
