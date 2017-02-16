package net.bizare.lunchvoteapp.web;

import net.bizare.lunchvoteapp.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandlingController {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandlingController.class);

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        LOG.error("Request: " + req.getRequestURL() + " raised " + ex);
        return getModel(ex.getMessage());
    }

    @ExceptionHandler(PersistenceException.class)
    public ModelAndView handleSQLError(HttpServletRequest req, Exception ex) {
        LOG.error("Request: " + req.getRequestURL() + " raised " + ex);
        return getModel("Current email is already exists");
    }

    private ModelAndView getModel(String errorMessage) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", errorMessage);
        mav.setViewName("error");
        return mav;
    }
}