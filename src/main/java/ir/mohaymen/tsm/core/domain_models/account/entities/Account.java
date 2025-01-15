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
    private String accountNumber;
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
        if (accountNumber == null || accountNumber.isBlank()) {
            SecureRandom random = new SecureRandom();
            byte[] buffer = new byte[14];
            random.nextBytes(buffer);
            String encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(buffer);
            accountNumber = encoded.substring(0, Math.min(encoded.length(), 14));
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
}
