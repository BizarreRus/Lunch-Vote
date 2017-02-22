package net.bizare.lunchvoteapp.web;

import net.bizare.lunchvoteapp.util.exception.ErrorInfo;
import net.bizare.lunchvoteapp.util.exception.OnlyOneVoteException;
import net.bizare.lunchvoteapp.util.exception.PermissibleTimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionInfoHandler {

    private static Logger LOG = LoggerFactory.getLogger(ExceptionInfoHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OnlyOneVoteException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    public ErrorInfo handleOnlyOneVote(HttpServletRequest req, OnlyOneVoteException e) {
        return logAndGetErrorInfo(req, e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PermissibleTimeException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    public ErrorInfo handlePermissibleTime(HttpServletRequest req, PermissibleTimeException e) {
        return logAndGetErrorInfo(req, e);
    }

    private ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e) {
        LOG.warn("Exception at request " + req.getRequestURL() + ": " + e.toString());
        return new ErrorInfo(req.getRequestURL(), e);
    }
}
