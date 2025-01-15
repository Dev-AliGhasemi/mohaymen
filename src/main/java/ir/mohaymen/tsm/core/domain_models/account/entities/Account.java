package ir.mohaymen.tsm.core.domain_models.account.entities;

import ir.mohaymen.tsm.core.domain_models.account.value_objects.IdentificationCode;
import ir.mohaymen.tsm.core.domain_models.account.value_objects.PhoneNumber;
import ir.mohaymen.tsm.core.domain_models.account.value_objects.PostalCode;
import ir.mohaymen.tsm.framework.domain_models.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.security.SecureRandom;
import java.sql.Date;
import java.util.Base64;

@Table(name = "accounts", schema = "tsm", indexes = @Index(name = "idx_account_number", columnList = "account_number"))
@Entity
@NoArgsConstructor
public class Account extends BaseEntity<Account> {
    @Column(name = "account_number", nullable = false, unique = true, length = 14, updatable = false)
    private Long accountNumber;
    @Column(name = "customer_name", nullable = false)
    private String customerName;
    @Embedded
    private IdentificationCode identificationCode;
    @Column(name = "date", nullable = false, updatable = false)
    private Date date;
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
    @Column(name = "creation_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date creationDate;



    @PrePersist
    private void generateAccountNumber() {
        if (accountNumber == null) {
            SecureRandom random = new SecureRandom();
            StringBuilder accountNumberBuilder = new StringBuilder();

            for (int i = 0; i < 14; i++) {
                int digit = random.nextInt(10);
                accountNumberBuilder.append(digit);
            }
            accountNumber = Long.parseLong(accountNumberBuilder.toString());
        }
    }

    public Account(String customerName, IdentificationCode identificationCode, Date date, PhoneNumber phoneNumber,
                   String address, PostalCode postalCode, CustomerType customerType) {
        this.customerName = customerName;
        this.identificationCode = identificationCode;
        this.date = date;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.postalCode = postalCode;
        this.customerType = customerType;
        invariantValidation();
    }

    @Override
    protected void invariantValidation() {
        if (customerName == null || customerName.isBlank())
            throw new IllegalArgumentException("customer name must not be null or blank");
        else if (identificationCode == null)
            throw new IllegalArgumentException("identification code must not be null");
        else if (date == null)
            throw new IllegalArgumentException("date must not be null");
        else if (phoneNumber == null)
            throw new IllegalArgumentException("phone number must not be null");
        else if (address == null || address.isBlank())
            throw new IllegalArgumentException("address must not be null or blank");
        else if (postalCode == null)
            throw new IllegalArgumentException("postal code must not be null");
        else if (customerType == null)
            throw new IllegalArgumentException("customer type must not be null");
    }

    public void changeDate(Date date){
        this.date = date;
        invariantValidation();
    }

    public void changeAddress(String address) {
        this.address = address;
        invariantValidation();
    }

    public void changeCustomerName(String customerName) {
        this.customerName = customerName;
        invariantValidation();
    }

    public void changePhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber=phoneNumber;
        invariantValidation();
    }

    public void changeIdentificationCode(IdentificationCode identificationCode) {
        this.identificationCode=identificationCode;
        invariantValidation();
    }

    public void changePostalCode(PostalCode postalCode) {
        this.postalCode = postalCode;
        invariantValidation();
    }

    public void changeCustomerType(CustomerType customerType) {
        this.customerType = customerType;
        invariantValidation();
    }
}
