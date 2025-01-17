package ir.mohaymen.tsm.endpoint.controllers.account;

import ir.mohaymen.tsm.core.application_services.account.command_handlers.CreateHandler;
import ir.mohaymen.tsm.core.application_services.account.command_handlers.PartialUpdateHandler;
import ir.mohaymen.tsm.core.domain_models.account.commands.Create;
import ir.mohaymen.tsm.core.domain_models.account.commands.PartialUpdate;
import ir.mohaymen.tsm.endpoint.handler.RequestHandler;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    private CreateHandler createHandler;
    private PartialUpdateHandler partialUpdateHandler;
    public AccountController(CreateHandler createHandler,PartialUpdateHandler partialUpdateHandler) {
        this.createHandler = createHandler;
        this.partialUpdateHandler = partialUpdateHandler;
    }


    @PostMapping
    public void createAccount(@RequestBody Create create){
         RequestHandler.handleRequest(create,createHandler::handle);
    }

    @PatchMapping("/{accountNumber}")
    public void updateAccount(@PathVariable Long accountNumber, @RequestBody PartialUpdate partialUpdate){
        partialUpdate.setAccountNumber(accountNumber);
        RequestHandler.handleRequest(partialUpdate,partialUpdateHandler::handle);
    }
}
