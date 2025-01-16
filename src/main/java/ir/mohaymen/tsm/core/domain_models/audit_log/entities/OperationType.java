package ir.mohaymen.tsm.core.domain_models.audit_log.entities;

public enum OperationType {
    CREATE("CREATE"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    private String value;

    OperationType(String string) {
        this.value = string;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
