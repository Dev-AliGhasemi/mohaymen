package ir.mohaymen.tsm.core.domain_models.account.value_objects;

import ir.mohaymen.tsm.framework.value_objects.BaseValueObjects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Getter
@ToString
public class IdentificationCode extends BaseValueObjects<IdentificationCode> {
    @Column(name = "identification_code", nullable = false, unique = true, length = 10)
    private String code;

    public IdentificationCode(String code){
        if (code == null || code.isBlank() || code.length() != 10)
            throw new IllegalArgumentException("identification code must be 10 digits long and not null or blank");
        this.code = code;
    }

    @Override
    protected int getHashCode() {
        return Objects.hashCode(code);
    }

    @Override
    protected boolean isEqual(IdentificationCode obj) {
        return obj.getCode().equals(code);
    }
}
