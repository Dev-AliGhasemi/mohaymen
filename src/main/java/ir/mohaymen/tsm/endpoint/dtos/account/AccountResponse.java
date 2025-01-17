package ir.mohaymen.tsm.endpoint.dtos.account;

import ir.mohaymen.tsm.core.domain_models.account.entities.Account;
import ir.mohaymen.tsm.core.domain_models.account.entities.AccountStatus;
import ir.mohaymen.tsm.core.domain_models.account.entities.CustomerType;
import lombok.Data;

import java.sql.Date;

@Data
public class AccountResponse {
    private String customerName;
    private String identificationCode;
    private Date date;
    private String phoneNumber;
    private String address;
    private String postalCode;
    private CustomerType customerType;
    private AccountStatus accountStatus;
    private Date modifiedAt;


    public AccountResponse(Account account) {
        this.customerName = account.getCustomerName();
        this.identificationCode = account.getIdentificationCode().getCode();
        this.date = account.getDate();
        this.phoneNumber = account.getPhoneNumber().getPhoneNumber();
        this.address = account.getAddress();
        this.postalCode = account.getPostalCode().getPostalCode();
        this.customerType = account.getCustomerType();
        this.accountStatus = account.getAccountStatus();
        this.modifiedAt = account.getModifiedAt();
    }

}
