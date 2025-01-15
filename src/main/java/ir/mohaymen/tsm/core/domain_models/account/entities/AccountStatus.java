package ir.mohaymen.tsm.core.domain_models.account.entities;

public enum AccountStatus {
    ACTIVE(0),
    BLOCKED(1),
    CLOSED(2);

    private byte value = 0;
    AccountStatus(int value) {
        this.value = (byte) value;
    }
}
