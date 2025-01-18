package ir.mohaymen.tsm.endpoint.dtos.account;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ir.mohaymen.tsm.core.domain_models.account.entities.Account;
import ir.mohaymen.tsm.core.domain_models.account.entities.AccountStatus;
import ir.mohaymen.tsm.core.domain_models.account.entities.CustomerType;
import ir.mohaymen.tsm.core.domain_models.transaction.entities.JalaliDateSerializer;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountResponse {
    private String customerName;
    private String identificationCode;
    @JsonSerialize(using = JalaliDateSerializer.class)
    private LocalDate date;
    private String phoneNumber;
    private String address;
    private String postalCode;
    private CustomerType customerType;
    private AccountStatus accountStatus;
    @JsonSerialize(using = JalaliDateSerializer.class)
    private LocalDate modifiedAt;


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
