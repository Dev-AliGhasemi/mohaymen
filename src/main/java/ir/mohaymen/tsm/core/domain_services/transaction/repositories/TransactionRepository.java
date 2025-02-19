package ir.mohaymen.tsm.core.domain_services.transaction.repositories;

import ir.mohaymen.tsm.core.domain_models.transaction.entities.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    Transaction findById(long id);
    Page<Transaction> findAll(Specification<Transaction> specification, Pageable pageable);
}
