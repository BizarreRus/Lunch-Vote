package net.bizare.lunchvoteapp.util.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value= HttpStatus.FORBIDDEN)
//@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="No such Order")
public class NotEnoughRigthsException extends DataAccessException {
    public NotEnoughRigthsException(String msg) {
        super(msg);
    }
}
