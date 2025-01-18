package ir.mohaymen.tsm.core.application_services.transaction.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCriteria {
    private String key;
    private Operation operation;
    private Object value;
}

