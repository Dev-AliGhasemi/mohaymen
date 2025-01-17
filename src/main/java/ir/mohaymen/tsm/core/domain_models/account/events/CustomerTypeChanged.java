package ir.mohaymen.tsm.core.domain_models.account.events;

import ir.mohaymen.tsm.core.domain_models.account.entities.CustomerType;
import ir.mohaymen.tsm.framework.events.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerTypeChanged implements Event {
    private Long id;
    private CustomerType customerType;
}
