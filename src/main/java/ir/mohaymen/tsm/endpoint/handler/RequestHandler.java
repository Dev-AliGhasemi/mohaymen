package ir.mohaymen.tsm.endpoint.handler;

import ir.mohaymen.tsm.framework.domain_models.command.Command;
import org.springframework.http.ResponseEntity;

import java.util.function.Consumer;

public class RequestHandler {
    public static  <T extends Command> ResponseEntity handleRequest(T request, Consumer<T> consumer) {
            consumer.accept(request);
            return ResponseEntity.ok().build();
    }
}
