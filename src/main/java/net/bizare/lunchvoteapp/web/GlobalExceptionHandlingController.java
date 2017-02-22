package net.bizare.lunchvoteapp.web;

import net.bizare.lunchvoteapp.AuthorizedUser;
import net.bizare.lunchvoteapp.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandlingController {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandlingController.class);

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ModelAndView handleNotFound(HttpServletRequest req, NotFoundException ex) {
        LOG.error("Request: " + req.getRequestURL() + " raised " + ex);
        return getModel(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
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