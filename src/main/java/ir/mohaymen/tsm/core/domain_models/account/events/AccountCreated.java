package ir.mohaymen.tsm.core.domain_models.account.events;

import ir.mohaymen.tsm.core.domain_models.account.entities.CustomerType;
import ir.mohaymen.tsm.core.domain_models.account.value_objects.IdentificationCode;
import ir.mohaymen.tsm.core.domain_models.account.value_objects.PhoneNumber;
import ir.mohaymen.tsm.core.domain_models.account.value_objects.PostalCode;
import ir.mohaymen.tsm.framework.domain_models.events.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class AccountCreated implements Event {
    private Long accountNumber;
    private String customerName;
    private IdentificationCode identificationCode;
    private Date date;
    private PhoneNumber phoneNumber;
    private String address;
    private PostalCode postalCode;
    private CustomerType customerType;
}
