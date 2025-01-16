package ir.mohaymen.tsm.core.domain_models.account.value_objects;

import ir.mohaymen.tsm.framework.domain_models.value_objects.BaseValueObjects;
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

public class PhoneNumber extends BaseValueObjects<PhoneNumber> {
    @Column(name = "phoneNumber", nullable = false, unique = true, length = 11)
    private String phoneNumber;

    public PhoneNumber(String phoneNumber){
        if (phoneNumber == null || phoneNumber.isBlank() || phoneNumber.length() != 11)
            throw new IllegalArgumentException("phone number must be 11 digits long and not null or blank");
        this.phoneNumber = phoneNumber;
    }

    @Override
    protected int getHashCode() {
        return Objects.hashCode(phoneNumber);
    }

    @Override
    protected boolean isEqual(PhoneNumber obj) {
        return obj.getPhoneNumber().equals(phoneNumber);
    }
}
