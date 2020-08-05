package softuni.springexam.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.springexam.service.ProductService;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    // constants must in another class but no time left :)
    private static final String CATEGORY_FOOD = "Food";
    private static final String CATEGORY_DRINK = "Drink";
    private static final String CATEGORY_HOUSEHOLD = "Household";
    private static final String CATEGORY_OTHER = "Other";
    private static final String REDIRECT_LOGIN = "redirect:/users/login";
    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ModelAndView getIndex(ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            modelAndView.setViewName(REDIRECT_LOGIN);
        } else {

            modelAndView.setViewName("index");
        }

        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("redirect:/users/login");
        } else {
            modelAndView.addObject("food", this.productService.findProductsByCategory(CATEGORY_FOOD));
            modelAndView.addObject("drink", this.productService.findProductsByCategory(CATEGORY_DRINK));
            modelAndView.addObject("household", this.productService.findProductsByCategory(CATEGORY_HOUSEHOLD));
            modelAndView.addObject("other", this.productService.findProductsByCategory(CATEGORY_OTHER));
            modelAndView.addObject("totalPrice", this.productService.calculateTotalPrice());
            modelAndView.setViewName("home");
        }

        return modelAndView;
    }
}
