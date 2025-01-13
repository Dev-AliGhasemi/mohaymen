package ir.mohaymen.tsm.core.domain_models.account.value_objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class IdentificationCode {
    @Column(name = "identification_code", nullable = false, unique = true, length = 10)
    private String code;

    private IdentificationCode(String code){
        if (code == null || code.isBlank() || code.length() != 10)
            throw new IllegalArgumentException("identification code must be 10 digits long and not null or blank");
        this.code = code;
    }
}
