package ir.mohaymen.tsm.core.domain_services.account.repositories;

import ir.mohaymen.tsm.core.domain_models.account.entities.Account;
import ir.mohaymen.tsm.core.domain_models.account.entities.AccountStatus;
import ir.mohaymen.tsm.core.domain_models.account.entities.CustomerType;
import ir.mohaymen.tsm.core.domain_models.account.value_objects.IdentificationCode;
import ir.mohaymen.tsm.core.domain_models.account.value_objects.PhoneNumber;
import ir.mohaymen.tsm.core.domain_models.account.value_objects.PostalCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByIdentificationCode(IdentificationCode identificationCode);

    Account findByAccountNumber(Long accountNumber);

//    @Transactional
//    @Modifying
//    @Query("""
//            update Account a set a.customerName = :customerName, a.phoneNumber = :phoneNumber, a.customerType = :customerType, a.address = :address
//                        , a.postalCode = :postalCode, a.date = :date, a.accountStatus = :accountStatus, a.identificationCode= :identificationCode
//            where a.accountNumber = :accountNumber """)
//    int update(@Param("accountNumber") Long accountNumber, @Param("customerName") String customerName, @Param("phoneNumber") String phoneNumber,
//               @Param("customerType") CustomerType customerType, @Param("address") String address, @Param("postalCode") String postalCode,
//               @Param("date") Date date, @Param("identificationCode") String identificationCode, @Param("accountStatus") AccountStatus accountStatus);
}
