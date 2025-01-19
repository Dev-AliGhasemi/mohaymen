package ir.mohaymen.tsm.core.domain_models.account.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ir.mohaymen.tsm.core.domain_models.account.events.*;
import ir.mohaymen.tsm.core.domain_models.account.value_objects.IdentificationCode;
import ir.mohaymen.tsm.core.domain_models.account.value_objects.PhoneNumber;
import ir.mohaymen.tsm.core.domain_models.account.value_objects.PostalCode;
import ir.mohaymen.tsm.core.application_services.audit_log.services.AuditLogListener;
import ir.mohaymen.tsm.core.domain_models.transaction.entities.JalaliDateSerializer;
import ir.mohaymen.tsm.framework.entities.BaseEntity;
import ir.mohaymen.tsm.framework.events.Event;
import ir.mohaymen.tsm.framework.exception.IllegalAccountException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "accounts", schema = "tsm", indexes = @Index(name = "idx_account_number", columnList = "account_number"))
@Entity
@NoArgsConstructor
@EntityListeners(AuditLogListener.class)
@Getter
public class Account extends BaseEntity<Account> {
    @Column(name = "account_number", nullable = false, unique = true, length = 14, updatable = false)
    private Long accountNumber;
    @Column(name = "customer_name", nullable = false)
    private String customerName;
    @Embedded
    private IdentificationCode identificationCode;
    @Column(name = "date", nullable = false, updatable = false)
    private LocalDate date;
    @Embedded
    private PhoneNumber phoneNumber;
    @Column(name = "address", nullable = false, length = 1000)
    private String address;
    @Embedded
    private PostalCode postalCode;
    @Column(name = "customer_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CustomerType customerType = CustomerType.PRIVATE;
    @Column(name = "account_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus = AccountStatus.ACTIVE;
    @Column(name = "created_at", nullable = false,updatable = false)
    @CreationTimestamp
    @JsonSerialize(using = JalaliDateSerializer.class)
    private LocalDate createdAt;
    @Column(name = "modified_at")
    @UpdateTimestamp
    @JsonSerialize(using = JalaliDateSerializer.class)
    private LocalDate modifiedAt;
    @Column(name = "amount")
    private BigDecimal amount = new BigDecimal(0);


    public Account(Long accountNumber, String customerName, IdentificationCode identificationCode, LocalDate date, PhoneNumber phoneNumber,
                   String address, PostalCode postalCode, CustomerType customerType) {
        handleEvent(new AccountCreated(accountNumber,customerName,identificationCode,date,phoneNumber,address,postalCode,customerType));
    }

    @Override
    protected void setStateByEvent(Event event) {
        if (event instanceof AccountCreated accountCreated) {
            this.accountNumber = accountCreated.getAccountNumber();
            this.customerName = accountCreated.getCustomerName();
            this.identificationCode = accountCreated.getIdentificationCode();
            this.date = accountCreated.getDate();
            this.phoneNumber = accountCreated.getPhoneNumber();
            this.address = accountCreated.getAddress();
            this.postalCode = accountCreated.getPostalCode();
            this.customerType = accountCreated.getCustomerType();
        }else if (event instanceof DateChanged dateChanged)
            this.date = dateChanged.getDate();
        else if (event instanceof PhoneNumberChanged phoneNumberChanged)
            this.phoneNumber = phoneNumberChanged.getPhoneNumber();
        else if (event instanceof AddressChanged addressChanged)
            this.address = addressChanged.getAddress();
        else if (event instanceof PostalCodeChanged postalCodeChanged)
            this.postalCode = postalCodeChanged.getPostalCode();
        else if (event instanceof CustomerTypeChanged customerTypeChanged)
            this.customerType = customerTypeChanged.getCustomerType();
        else if (event instanceof AmountChanged amountChanged)
            this.amount = amountChanged.getAmount();
        else if (event instanceof CustomerNameChanged customerNameChanged)
            this.customerName = customerNameChanged.getCustomerName();
        else if (event instanceof IdentificationCodeChanged identificationCodeChanged)
            this.identificationCode = identificationCodeChanged.getIdentificationCode();
        else if (event instanceof PostalCodeChanged postalCodeChanged)
            this.postalCode = postalCodeChanged.getPostalCode();
    }

    @Override
    protected void invariantValidation() {
        if (customerName == null || customerName.isBlank())
            throw new IllegalAccountException("customer name must not be null or blank");
        else if (identificationCode == null)
            throw new IllegalAccountException("identification code must not be null");
        else if (date == null)
            throw new IllegalAccountException("date must not be null");
        else if (phoneNumber == null)
            throw new IllegalAccountException("phone number must not be null");
        else if (address == null || address.isBlank())
            throw new IllegalAccountException("address must not be null or blank");
        else if (postalCode == null)
            throw new IllegalAccountException("postal code must not be null");
        else if (customerType == null)
            throw new IllegalAccountException("customer type must not be null");
        else if (accountNumber == null)
            throw new IllegalAccountException("accountNumber must not be null");
    }

    public void changeDate(LocalDate date){
        handleEvent(new DateChanged(id,date));
    }

    public void changeAddress(String address) {
        handleEvent(new AddressChanged(id,address));
    }

    public void changeCustomerName(String customerName) {
        handleEvent(new CustomerNameChanged(id,customerName));
    }

    public void changePhoneNumber(PhoneNumber phoneNumber) {
        handleEvent(new PhoneNumberChanged(id,phoneNumber));
    }

    public void changeIdentificationCode(IdentificationCode identificationCode) {
        handleEvent(new IdentificationCodeChanged(id,identificationCode));
    }

    public void changePostalCode(PostalCode postalCode) {
        handleEvent(new PostalCodeChanged(id,postalCode));
    }

    public void changeCustomerType(CustomerType customerType) {
        handleEvent(new CustomerTypeChanged(id,customerType));
    }

    public void changeAmount(BigDecimal amount) {
        handleEvent(new AmountChanged(id,amount));
    }
}
