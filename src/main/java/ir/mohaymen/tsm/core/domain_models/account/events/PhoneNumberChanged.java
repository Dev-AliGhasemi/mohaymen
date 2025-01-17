package ir.mohaymen.tsm.core.domain_models.account.events;

import ir.mohaymen.tsm.core.domain_models.account.value_objects.PhoneNumber;
import ir.mohaymen.tsm.framework.events.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhoneNumberChanged implements Event {
    private Long id;
    private PhoneNumber phoneNumber;
}
