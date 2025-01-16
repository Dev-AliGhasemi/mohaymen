package ir.mohaymen.tsm.endpoint.handler;

import ir.mohaymen.tsm.framework.domain_models.commands.Command;

import java.util.function.Consumer;

public class RequestHandler {
    public static  <T extends Command> void handleRequest(T request, Consumer<T> consumer) {
            consumer.accept(request);
    }
}
