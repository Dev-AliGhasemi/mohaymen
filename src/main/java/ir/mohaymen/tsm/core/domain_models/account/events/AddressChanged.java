package ir.mohaymen.tsm.core.domain_models.account.events;

import ir.mohaymen.tsm.framework.domain_models.events.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class AddressChanged implements Event {
    private Long id;
    private String address;
}
