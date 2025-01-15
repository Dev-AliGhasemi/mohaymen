package ir.mohaymen.tsm.core.domain_services.account.repositories;

import ir.mohaymen.tsm.core.domain_models.account.entities.Account;
import ir.mohaymen.tsm.core.domain_models.account.value_objects.IdentificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByIdentificationCode(IdentificationCode identificationCode);
}
