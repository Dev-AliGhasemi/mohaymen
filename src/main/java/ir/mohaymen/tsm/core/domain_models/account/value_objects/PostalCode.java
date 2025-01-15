package ir.mohaymen.tsm.core.domain_models.account.value_objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
@Getter
public class PostalCode {
    @Column(name = "postalCode", nullable = false, length = 10)
    private String postalCode;
    public PostalCode(String postalCode){
        if (postalCode == null || postalCode.isBlank() || postalCode.length() != 10)
            throw new IllegalArgumentException("postal code must be 10 digits long and not null or blank");
        this.postalCode = postalCode;
    }
}
