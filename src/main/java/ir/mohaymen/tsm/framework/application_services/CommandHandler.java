package ir.mohaymen.tsm.framework.application_services;

import org.springframework.stereotype.Component;

public interface CommandHandler<T,K> {
    K handle(T command);
}
