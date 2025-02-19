package ir.mohaymen.tsm.core.domain_models.account.events;

import ir.mohaymen.tsm.framework.events.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DateChanged implements Event {
    private Long id;
    private LocalDate date;
}
