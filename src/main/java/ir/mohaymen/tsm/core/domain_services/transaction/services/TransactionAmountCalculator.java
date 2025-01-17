package ir.mohaymen.tsm.core.domain_services.transaction.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@PropertySource(name = "config.properties", value = {"classpath:config.properties"})
@Service
public class TransactionAmountCalculator {
    private float x;
    private float y;
    private float z;

    public TransactionAmountCalculator(@Value("${x}") float x, @Value("${y}") float y, @Value("${z}") float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public BigDecimal calculate(BigDecimal amount) {
        BigDecimal karmozd = amount.multiply(new BigDecimal(x));
        if (karmozd.compareTo(new BigDecimal(y)) > 0)
            return amount.add(new BigDecimal(y));
        else if (karmozd.compareTo(new BigDecimal(z)) < 0)
            return amount.add(new BigDecimal(z));
        else
            return amount.add(karmozd);
    }

    public BigDecimal calculateKarmozd(BigDecimal amount) {
        BigDecimal karmozd = amount.multiply(new BigDecimal(x));
        if (karmozd.compareTo(new BigDecimal(y)) > 0)
            return new BigDecimal(y);
        else if (karmozd.compareTo(new BigDecimal(z)) < 0)
            return new BigDecimal(z);
        else
            return karmozd;
    }


}
