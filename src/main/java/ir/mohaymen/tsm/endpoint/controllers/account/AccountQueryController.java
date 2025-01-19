package ir.mohaymen.tsm.endpoint.controllers.account;

import ir.mohaymen.tsm.core.application_services.account.services.AccountQueryService;
import ir.mohaymen.tsm.endpoint.dtos.account.AccountNumberResponse;
import ir.mohaymen.tsm.endpoint.dtos.account.AccountResponse;
import ir.mohaymen.tsm.endpoint.dtos.account.AmountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountQueryController {

    private AccountQueryService accountQueryService;

    public AccountQueryController(AccountQueryService accountQueryService) {
        this.accountQueryService = accountQueryService;
    }

    @GetMapping("/account_number/{identificationCode}")
    public ResponseEntity<AccountNumberResponse> getAccountNumber(@PathVariable String identificationCode) {
        return ResponseEntity.ok(new AccountNumberResponse(accountQueryService.getAccountNumber(identificationCode)));
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable Long accountNumber) {
        return ResponseEntity.ok(new AccountResponse(accountQueryService.getAccount(accountNumber)));
    }

    @GetMapping("/amount/{accountNumber}")
    public ResponseEntity<AmountResponse> getAmount(@PathVariable Long accountNumber) {
        return ResponseEntity.ok(new AmountResponse(accountQueryService.getAmount(accountNumber)));
    }
}
