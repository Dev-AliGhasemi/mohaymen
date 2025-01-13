package ir.mohaymen.tsm.core.domain_models.account.value_objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class PhoneNumber {
    @Column(name = "phoneNumber", nullable = false, unique = true, length = 11)
    private String phoneNumber;
    private PhoneNumber(String phoneNumber){
        if (phoneNumber == null || phoneNumber.isBlank() || phoneNumber.length() != 11)
            throw new IllegalArgumentException("phone number must be 11 digits long and not null or blank");
        this.phoneNumber = phoneNumber;
    }
}
