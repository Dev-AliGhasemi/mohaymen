package ir.mohaymen.tsm.endpoint.exception_handlers;

import ir.mohaymen.tsm.endpoint.dtos.ErrorResponse;
import ir.mohaymen.tsm.endpoint.dtos.transaction.TransactionFailedResponse;
import ir.mohaymen.tsm.framework.exception.IllegalAccountException;
import ir.mohaymen.tsm.framework.exception.IllegalTransactionException;
import jakarta.persistence.PersistenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.logging.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllException(Exception ex) {
        Logger.getGlobal().severe("An error occurred:"+ ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Please try later", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalAccountException.class)
    public ResponseEntity<ErrorResponse> handleAccountException(IllegalAccountException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(IllegalTransactionException.class)
    public ResponseEntity<ErrorResponse> handleTransactionException(IllegalTransactionException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<ErrorResponse> handleTransactionException(PersistenceException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}

