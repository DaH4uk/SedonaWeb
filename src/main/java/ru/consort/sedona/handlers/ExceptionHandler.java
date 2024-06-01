package ru.consort.sedona.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ExceptionHandler implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    public ModelAndView resolveException(jakarta.servlet.http.HttpServletRequest request,
                                         jakarta.servlet.http.HttpServletResponse response, Object handler,
                                         Exception ex) {
        logger.error("ErrorLog: ", ex);
        return new ModelAndView("error/error", "exceptionMsg", "ExceptionHandler msg: " + ex.toString());
    }
}
