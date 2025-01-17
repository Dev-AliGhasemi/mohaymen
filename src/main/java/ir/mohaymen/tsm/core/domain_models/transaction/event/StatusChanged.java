package ir.mohaymen.tsm.core.domain_models.transaction.event;

import ir.mohaymen.tsm.core.domain_models.transaction.entities.Status;
import ir.mohaymen.tsm.framework.events.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusChanged implements Event {
    private Long id;
    private Status status;
}
