package ir.mohaymen.tsm.core.domain_models.transaction.entities;

public enum TransactionType {
    WITHDRAW("WITHDRAW"),
    DEPOSIT("DEPOSIT"),
    TRANSFER("TRANSFER"),
    KARMOZD("KARMOZD");

    private String type;

    TransactionType(String type) {
        this.type = type;
    }
}
