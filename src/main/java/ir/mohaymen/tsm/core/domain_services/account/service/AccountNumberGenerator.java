package ir.mohaymen.tsm.core.domain_services.account.service;

import java.security.SecureRandom;

public class AccountNumberGenerator {

    public static Long generateAccountNumber() {
        SecureRandom random = new SecureRandom();
        StringBuilder accountNumberBuilder = new StringBuilder();

        for (int i = 0; i < 14; i++) {
            int digit = random.nextInt(10);
            accountNumberBuilder.append(digit);
        }
        return Long.parseLong(accountNumberBuilder.toString());
    }
}
