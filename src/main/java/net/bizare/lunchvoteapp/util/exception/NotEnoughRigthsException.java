package net.bizare.lunchvoteapp.util.exception;

import org.springframework.dao.DataAccessException;

public class NotEnoughRigthsException extends DataAccessException {
    public NotEnoughRigthsException(String msg) {
        super(msg);
    }
}
