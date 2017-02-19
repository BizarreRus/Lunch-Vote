package net.bizare.lunchvoteapp.web;

import net.bizare.lunchvoteapp.AuthorizedUser;
import net.bizare.lunchvoteapp.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandlingController {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandlingController.class);

    @ExceptionHandler(NotFoundException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        LOG.error("Request: " + req.getRequestURL() + " raised " + ex);
        return getModel(ex.getMessage());
    }

    @ExceptionHandler(PersistenceException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    public ModelAndView handleSQLError(HttpServletRequest req, Exception ex) {
        LOG.error("Request: " + req.getRequestURL() + " raised " + ex);
        return getModel("Current email is already exists");
    }

    @ExceptionHandler(Exception.class)
    @Order(Ordered.LOWEST_PRECEDENCE)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception ex) throws Exception {
        LOG.error("Request: " + req.getRequestURL() + " raised " + ex);
        String  message = ex.getClass().getSimpleName() + ": " + ex.getLocalizedMessage();

        AuthorizedUser authorizedUser = AuthorizedUser.safeGet();
        if (authorizedUser != null) {
            return getModel(message).addObject("userTo", authorizedUser.getUserTo());
        }
        return getModel(message);
    }

    private ModelAndView getModel(String errorMessage) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", errorMessage);
        mav.setViewName("error");
        return mav;
    }
}