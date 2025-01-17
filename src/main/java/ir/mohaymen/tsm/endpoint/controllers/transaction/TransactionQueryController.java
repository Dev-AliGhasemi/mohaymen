package ir.mohaymen.tsm.endpoint.controllers.transaction;

import ir.mohaymen.tsm.core.application_services.transaction.dtos.TransactionStatus;
import ir.mohaymen.tsm.core.application_services.transaction.services.TransactionQueryService;
import ir.mohaymen.tsm.endpoint.dtos.transaction.TransactionStatusResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(new TransactionStatusResponse(transactionStatus.getStatus(),transactionStatus.getMessage()));
    }
}
