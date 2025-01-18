package ir.mohaymen.tsm.endpoint.controllers.transaction;

import ir.mohaymen.tsm.core.application_services.transaction.dtos.Operation;
import ir.mohaymen.tsm.core.application_services.transaction.dtos.SearchCriteria;
import ir.mohaymen.tsm.core.application_services.transaction.dtos.TransactionStatus;
import ir.mohaymen.tsm.core.application_services.transaction.services.TransactionQueryService;
import ir.mohaymen.tsm.core.domain_models.transaction.entities.Transaction;
import ir.mohaymen.tsm.core.domain_models.transaction.entities.TransactionType;
import ir.mohaymen.tsm.endpoint.dtos.transaction.PagedTransactionResponse;
import ir.mohaymen.tsm.endpoint.dtos.transaction.TransactionStatusResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionQueryController {

    private final TransactionQueryService transactionQueryService;

    public TransactionQueryController(TransactionQueryService transactionQueryService) {
        this.transactionQueryService = transactionQueryService;
    }


    @GetMapping("/{transactionNumber}")
    public ResponseEntity<TransactionStatusResponse> getStatus(@PathVariable Long transactionNumber) {
        TransactionStatus transactionStatus = transactionQueryService.getTransactionStatus(transactionNumber);
        return ResponseEntity.ok(new TransactionStatusResponse(transactionStatus.getStatus(), transactionStatus.getMessage()));
    }

    @GetMapping
    public PagedTransactionResponse getTransactions(
            @RequestParam(required = false) Long sourceAccount,
            @RequestParam(required = false) Long destinationAccount,
            @RequestParam(required = false) TransactionType type,
            @RequestParam(required = false) BigDecimal minAmount,
            @RequestParam(required = false) BigDecimal maxAmount,
            @RequestParam(required = false) boolean top,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {


        List<SearchCriteria> filters = new ArrayList<>();
        if (sourceAccount != null)
            filters.add(new SearchCriteria("sourceAccountNumber", Operation.IS, sourceAccount));
        if (destinationAccount != null)
            filters.add(new SearchCriteria("destinationAccountNumber", Operation.IS, destinationAccount));
        if (type != null)
            filters.add(new SearchCriteria("type", Operation.IS, type));
        if (minAmount != null)
            filters.add(new SearchCriteria("amount", Operation.GREATER, minAmount));
        if (maxAmount != null)
            filters.add(new SearchCriteria("amount", Operation.LESSER, maxAmount));
        if (startDate != null)
            filters.add(new SearchCriteria("createdAt", Operation.GREATER, startDate));
        if (endDate != null)
            filters.add(new SearchCriteria("createdAt", Operation.LESSER, endDate));

        Pageable pageable;
        if (top){
            pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
            return new PagedTransactionResponse(transactionQueryService.getFilteredTransactions(filters, pageable));
        } else{
            pageable = Pageable.ofSize(10);
            return new PagedTransactionResponse(transactionQueryService.getFilteredTransactions(filters, pageable));
        }
    }
}
