package net.bizare.lunchvoteapp.web;

import net.bizare.lunchvoteapp.util.exception.NotEnoughRigthsException;
import net.bizare.lunchvoteapp.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandlingController {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandlingController.class);

    @ExceptionHandler({NotEnoughRigthsException.class, NotFoundException.class})
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        LOG.error("Request: " + req.getRequestURL() + " raised " + ex);
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.setViewName("error");
        return mav;
    }
}