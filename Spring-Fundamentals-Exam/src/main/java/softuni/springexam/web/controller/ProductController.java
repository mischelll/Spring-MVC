package softuni.springexam.web.controller;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.springexam.service.ProductService;
import softuni.springexam.service.model.ProductAddServiceModel;
import softuni.springexam.web.model.ProductAddModel;
import softuni.springexam.web.model.UserLoginModel;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
    // constants must in another class but no time left :)
    private static final String REDIRECT_REGISTER = "redirect:/users/register";
    private static final String REDIRECT_LOGIN = "redirect:/users/login";
    private static final String REDIRECT_HOME = "redirect:/home";
    private final ModelMapper mapper;
    private final ProductService productService;

    public ProductController(ModelMapper mapper, ProductService productService) {
        this.mapper = mapper;
        this.productService = productService;
    }

    @GetMapping("/add")
    public ModelAndView getAdd(Model model, ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName(REDIRECT_LOGIN);
        } else {
            if (!model.containsAttribute("addProduct")) {
                model.addAttribute("addProduct", new ProductAddModel());
            }
            modelAndView.setViewName("product-add");
        }

        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView getAddConfirm(@Valid @ModelAttribute("addProduct") ProductAddModel addProduct,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes,
                                      HttpSession httpSession,
                                      ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addProduct", addProduct);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addProduct", bindingResult);
            modelAndView.setViewName("redirect:/products/add");
        } else {
            ProductAddServiceModel productMap = this.mapper.map(addProduct, ProductAddServiceModel.class);

            ProductAddServiceModel addServiceModel = this.productService.add(productMap);
            if (addServiceModel == null) {
                redirectAttributes.addFlashAttribute("addProduct", addProduct);
                redirectAttributes.addFlashAttribute("found", true);
                modelAndView.setViewName("redirect:/products/add");
            } else {
                modelAndView.setViewName(REDIRECT_HOME);
            }
        }

        return modelAndView;
    }

    @GetMapping("/buy")
    public ModelAndView buyProduct(@RequestParam("id") String id,  HttpSession httpSession, ModelAndView modelAndView) {
        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName(REDIRECT_LOGIN);
        } else {
            this.productService.buyProduct(id);
            modelAndView.setViewName(REDIRECT_HOME);
        }
        return modelAndView;

    }

    @GetMapping("/buy/all")
    public ModelAndView buyAll(Model model, HttpSession httpSession, ModelAndView modelAndView) {
        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName(REDIRECT_LOGIN);
        } else {
            this.productService.buyAllProducts();
            modelAndView.setViewName(REDIRECT_HOME);
        }

        return modelAndView;
    }


}
