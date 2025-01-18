package ir.mohaymen.tsm.endpoint.exception_handlers;

import ir.mohaymen.tsm.endpoint.dtos.ErrorResponse;
import ir.mohaymen.tsm.endpoint.dtos.transaction.TransactionFailedResponse;
import ir.mohaymen.tsm.framework.exception.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
//TODO repair


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<TransactionFailedResponse> handleIllegalArgumentException(TransactionException ex) {
        TransactionFailedResponse transactionFailedResponse = new TransactionFailedResponse(ex.getTransactionNumber(), ex.getMessage());
        return new ResponseEntity<>(transactionFailedResponse, HttpStatus.BAD_REQUEST);
    }
}

