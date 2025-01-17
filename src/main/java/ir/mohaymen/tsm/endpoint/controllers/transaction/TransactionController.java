package ir.mohaymen.tsm.endpoint.controllers.transaction;

import ir.mohaymen.tsm.core.application_services.transaction.command_handlers.CreateHandler;
import ir.mohaymen.tsm.core.domain_models.transaction.commands.Create;
import ir.mohaymen.tsm.endpoint.dtos.transaction.TransactionNumberResponse;
import ir.mohaymen.tsm.endpoint.handler.RequestHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private CreateHandler createHandler;

    public TransactionController(CreateHandler createHandler) {
        this.createHandler = createHandler;
    }


    @PostMapping
    public ResponseEntity<TransactionNumberResponse> createAccount(@RequestBody Create create){
         return RequestHandler.handleRequest(create, createReq -> {
             Long id = createHandler.handle(createReq);
             return ResponseEntity.ok(new TransactionNumberResponse(id));
         });
    }
}
