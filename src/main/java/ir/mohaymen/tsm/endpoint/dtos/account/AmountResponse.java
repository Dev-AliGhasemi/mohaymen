package ir.mohaymen.tsm.endpoint.dtos.account;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AmountResponse {
    private BigDecimal amount;
}
