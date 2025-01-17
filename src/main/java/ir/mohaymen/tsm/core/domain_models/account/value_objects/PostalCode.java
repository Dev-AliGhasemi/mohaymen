package ir.mohaymen.tsm.core.domain_models.account.value_objects;

import ir.mohaymen.tsm.framework.value_objects.BaseValueObjects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@NoArgsConstructor
@Embeddable
@Getter
@ToString
public class PostalCode extends BaseValueObjects<PostalCode> {
    @Column(name = "postalCode", nullable = false, length = 10)
    private String postalCode;
    public PostalCode(String postalCode){
        if (postalCode == null || postalCode.isBlank() || postalCode.length() != 10)
            throw new IllegalArgumentException("postal code must be 10 digits long and not null or blank");
        this.postalCode = postalCode;
    }

    @Override
    protected int getHashCode() {
        return Objects.hashCode(postalCode);
    }

    @Override
    protected boolean isEqual(PostalCode obj) {
        return obj.getPostalCode().equals(postalCode);
    }
}
