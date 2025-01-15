package ir.mohaymen.tsm.endpoint.controllers.account;

import ir.mohaymen.tsm.core.application_services.account.command_handlers.CreateHandler;
import ir.mohaymen.tsm.core.domain_models.account.commands.Create;
import ir.mohaymen.tsm.endpoint.handler.RequestHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private CreateHandler createHandler;
    public AccountController(CreateHandler createHandler) {
        this.createHandler = createHandler;
    }


    @PostMapping
    public ResponseEntity createAccount(@RequestBody Create create){
        return RequestHandler.handleRequest(create,createHandler::handle);
    }
}
