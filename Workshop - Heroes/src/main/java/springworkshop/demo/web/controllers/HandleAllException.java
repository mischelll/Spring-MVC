package springworkshop.demo.web.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import springworkshop.demo.errors.HeroNotFoundException;

@ControllerAdvice
public class HandleAllException {
    @ExceptionHandler(Throwable.class)
    public ModelAndView handleException(Throwable ex) {
        ModelAndView model = new ModelAndView("error");
        ex = new Throwable("Worng URL!");
        model.addObject(ex.getMessage());

        return model;
    }
}
