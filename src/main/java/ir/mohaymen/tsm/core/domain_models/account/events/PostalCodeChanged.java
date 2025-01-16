package ir.mohaymen.tsm.core.domain_models.account.events;

import ir.mohaymen.tsm.core.domain_models.account.value_objects.PostalCode;
import ir.mohaymen.tsm.framework.domain_models.events.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostalCodeChanged implements Event {
    private Long id;
    private PostalCode postalCode;
}
