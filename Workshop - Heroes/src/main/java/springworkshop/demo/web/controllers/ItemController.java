package springworkshop.demo.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/items")
public class ItemController {

    @GetMapping("/create")
    public ModelAndView getCreate(){
        return new ModelAndView("item/create-item");
    }

    @GetMapping("/merchant")
    public ModelAndView getMerchant(){
        return new ModelAndView("item/merchant");
    }
}
