package ir.mohaymen.tsm.core.domain_models.account.entities;

public enum CustomerType {
    PRIVATE("PRIVATE"),
    COMPANY("COMPANY");

    private String value = "PRIVATE";

    CustomerType(String value) {
        this.value = value;
    }
}
