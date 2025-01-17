package ir.mohaymen.tsm.endpoint.handler;

import ir.mohaymen.tsm.framework.commands.Command;
import org.springframework.http.ResponseEntity;

import java.util.function.Consumer;
import java.util.function.Function;


public class RequestHandler {
    public static  <T extends Command> void handleRequest(T request, Consumer<T> consumer) {
            consumer.accept(request);
    }

    public static  <T extends Command, K> ResponseEntity<K> handleRequest(T request, Function<T,ResponseEntity<K>> function) {
        return function.apply(request);
    }
}
