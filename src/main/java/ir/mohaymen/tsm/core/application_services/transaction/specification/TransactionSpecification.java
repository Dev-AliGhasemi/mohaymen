package ir.mohaymen.tsm.core.application_services.transaction.specification;

import ir.mohaymen.tsm.core.application_services.transaction.dtos.Operation;
import ir.mohaymen.tsm.core.application_services.transaction.dtos.SearchCriteria;
import ir.mohaymen.tsm.core.domain_models.transaction.entities.Transaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Date;

public class TransactionSpecification implements Specification<Transaction> {

    private final SearchCriteria criteria;

    public TransactionSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Transaction> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation() == Operation.GREATER) {
            if (root.get(criteria.getKey()).getJavaType() == java.sql.Date.class) {
                Date dateValue = Date.valueOf(criteria.getValue().toString());
                return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), dateValue);
            }else
                return builder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation() == Operation.LESSER) {
            if (root.get(criteria.getKey()).getJavaType() == java.sql.Date.class) {
                Date dateValue = Date.valueOf(criteria.getValue().toString());
                return builder.lessThanOrEqualTo(root.get(criteria.getKey()), dateValue);
            }else
                return builder.lessThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation() == Operation.IS) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}

