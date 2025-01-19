package ir.mohaymen.tsm.endpoint.exception_handlers;

import ir.mohaymen.tsm.endpoint.dtos.ErrorResponse;
import ir.mohaymen.tsm.framework.exception.IllegalAccountException;
import ir.mohaymen.tsm.framework.exception.IllegalTransactionException;
import jakarta.persistence.PersistenceException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.logging.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<ErrorResponse> handleAccountException(IllegalAccountException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ErrorResponse> handleTransactionException(IllegalTransactionException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ErrorResponse> handlePersistenceException(PersistenceException ex) {
        ErrorResponse errorResponse;
        if (ex.getCause() != null)
            errorResponse = new ErrorResponse(ex.getCause().getMessage(), HttpStatus.BAD_REQUEST.value());
        else
            errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ErrorResponse> handleDataAccessException(DataAccessException ex) {
        Logger.getGlobal().severe("An error occurred:" + ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Problem in access to data", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleAllException(Exception ex) {
        if (ex instanceof PersistenceException pe)
            return handlePersistenceException(pe);
        else if (ex instanceof IllegalAccountException iae)
            return handleAccountException(iae);
        else if (ex instanceof DataAccessException dae)
            return handleDataAccessException(dae);
        else if (ex instanceof IllegalTransactionException txe)
            return handleTransactionException(txe);
        else {
            Logger.getGlobal().severe("An error occurred:" + ex.getMessage());
            ErrorResponse errorResponse = new ErrorResponse("Please try later", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

