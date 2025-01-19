package ir.mohaymen.tsm.framework.exception;

import lombok.Getter;

@Getter
public class IllegalAccountException extends RuntimeException {
    public IllegalAccountException(String message) {
        super(message);
    }
}
