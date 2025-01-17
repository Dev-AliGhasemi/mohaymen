package ir.mohaymen.tsm.core.domain_services.transaction.services;

import java.security.SecureRandom;

public class TransactionNumberGenerator {

    public static Long generateTransactionNumber() {
        SecureRandom random = new SecureRandom();
        StringBuilder transactionNumberBuilder = new StringBuilder();

        for (int i = 0; i < 14; i++) {
            int digit = random.nextInt(10);
            transactionNumberBuilder.append(digit);
        }
        return Long.parseLong(transactionNumberBuilder.toString());
    }
}
