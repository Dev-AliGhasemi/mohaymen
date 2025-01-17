package ir.mohaymen.tsm.core.application_services.account.command_handlers;

import ir.mohaymen.tsm.core.domain_models.account.commands.Create;
import ir.mohaymen.tsm.core.domain_models.account.entities.Account;
import ir.mohaymen.tsm.core.domain_models.account.entities.CustomerType;
import ir.mohaymen.tsm.core.domain_models.account.value_objects.IdentificationCode;
import ir.mohaymen.tsm.core.domain_models.account.value_objects.PhoneNumber;
import ir.mohaymen.tsm.core.domain_models.account.value_objects.PostalCode;
import ir.mohaymen.tsm.core.domain_services.account.repositories.AccountRepository;
import ir.mohaymen.tsm.core.domain_services.account.service.AccountNumberGenerator;
import ir.mohaymen.tsm.framework.application_services.CommandHandler;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Component;

@Component("accountCreateHandler")
public class CreateHandler implements CommandHandler<Create,Void> {

    private final AccountRepository accountRepository;

    public CreateHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Void handle(Create create) {
        Account account = accountRepository.findByIdentificationCode(new IdentificationCode(create.getIdentificationCode()));
        if (account == null){
            Long accountNumber = AccountNumberGenerator.generateAccountNumber();
            account = new Account(accountNumber,create.getCustomerName(), new IdentificationCode(create.getIdentificationCode()),
                    create.getDate(),new PhoneNumber(create.getPhoneNumber()), create.getAddress(), new PostalCode(create.getPostalCode())
                    , CustomerType.valueOf(create.getCustomerType()));
            accountRepository.save(account);
        }else
            throw new EntityExistsException("Account is exists.");

        return null;
    }
}
