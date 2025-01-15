package ir.mohaymen.tsm.core.domain_services.account.service;

import ir.mohaymen.tsm.core.domain_models.account.commands.PartialUpdate;
import ir.mohaymen.tsm.core.domain_models.account.entities.Account;
import ir.mohaymen.tsm.core.domain_models.account.entities.CustomerType;
import ir.mohaymen.tsm.core.domain_models.account.value_objects.IdentificationCode;
import ir.mohaymen.tsm.core.domain_models.account.value_objects.PhoneNumber;
import ir.mohaymen.tsm.core.domain_models.account.value_objects.PostalCode;


public class AccountMapping {
    public static void map(Account account,PartialUpdate partialUpdate){
        if (partialUpdate.getDate() != null)
            account.changeDate(partialUpdate.getDate());
        if (partialUpdate.getAddress() != null)
            account.changeAddress(partialUpdate.getAddress());
        if (partialUpdate.getCustomerName() != null)
            account.changeCustomerName(partialUpdate.getCustomerName());
        if (partialUpdate.getPhoneNumber() != null)
            account.changePhoneNumber(new PhoneNumber(partialUpdate.getPhoneNumber()));
        if (partialUpdate.getIdentificationCode() != null)
            account.changeIdentificationCode(new IdentificationCode(partialUpdate.getIdentificationCode()));
        if (partialUpdate.getPostalCode() != null)
            account.changePostalCode(new PostalCode(partialUpdate.getPostalCode()));
        if (partialUpdate.getCustomerType() != null)
            account.changeCustomerType(CustomerType.valueOf(partialUpdate.getCustomerType()));
    }
}
