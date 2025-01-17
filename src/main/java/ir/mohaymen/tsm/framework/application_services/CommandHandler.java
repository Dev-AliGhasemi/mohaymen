package ir.mohaymen.tsm.framework.application_services;

public interface CommandHandler<T,K> {
    K handle(T command);
}
