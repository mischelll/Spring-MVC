package springworkshop.demo.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springworkshop.demo.data.models.Hero;
import springworkshop.demo.service.UserService;
import springworkshop.demo.service.models.UserServiceModel;
import springworkshop.demo.web.models.UserProfileViewModel;
import springworkshop.demo.web.models.UserRegisterModel;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @GetMapping("/register")
    public ModelAndView getRegister() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            /* The user is logged in :) */
            return new ModelAndView("redirect:/home");
        }
        return super.view("auth/register");
    }

    @PostMapping("/register")
    public ModelAndView getRegisterConfirm(@ModelAttribute UserRegisterModel user) {
        if (!this.userService.register(this.modelMapper.map(user, UserServiceModel.class))) {
            return super.view("auth/register");
        }
        return super.redirect("auth/login");
    }

    @GetMapping("/login")
    public ModelAndView getLogin(HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            /* The user is logged in :) */
            return new ModelAndView("redirect:/home");
        }

        return super.view("auth/login");
    }

    @GetMapping("/profile")
    public ModelAndView getProfile(Principal principal, ModelAndView modelAndView) {
        UserProfileViewModel map = this.modelMapper.map(
                this.userService.findByUsername(principal.getName()), UserProfileViewModel.class);
        modelAndView.addObject("user", map);
        return super.view("user/profile", modelAndView);
    }


}
