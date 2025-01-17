package ir.mohaymen.tsm.core.domain_models.account.events;

import ir.mohaymen.tsm.framework.events.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AmountChanged implements Event {
    private Long id;
    private BigDecimal amount;
}
