package ir.mohaymen.tsm.core.domain_models.transaction.entities;

public enum Status {
    SUCCESS("SUCCESS"),
    FAILED("FAILED");

    private String value="PENDING";

    Status(String value) {
        this.value = value;
    }


}
