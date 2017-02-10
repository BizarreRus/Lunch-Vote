package net.bizare.lunchvoteapp.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value= HttpStatus.NOT_ACCEPTABLE, reason="No such Order")
public class PermissibleTimeException extends RuntimeException {
    public PermissibleTimeException(String message) {
        super(message);
    }
}
