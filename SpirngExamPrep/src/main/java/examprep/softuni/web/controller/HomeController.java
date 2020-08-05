package examprep.softuni.web.controller;

import examprep.softuni.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class HomeController {
    private final ItemService itemService;

    public HomeController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping("/")
    public ModelAndView getIndex(ModelAndView modelAndView) {
        modelAndView.setViewName("index");

        return modelAndView;
    }

    @GetMapping("/home")

    public ModelAndView getHome(ModelAndView modelAndView, HttpSession httpSession, Principal principal) {
        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("redirect:/users/login");
        } else {
            modelAndView.addObject("showItems", this.itemService.showItems());
            modelAndView.setViewName("home");
        }

        return modelAndView;
    }
}
