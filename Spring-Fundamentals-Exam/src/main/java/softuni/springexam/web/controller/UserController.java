package softuni.springexam.web.controller;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.springexam.service.UserService;
import softuni.springexam.service.model.UserLoginServiceModel;
import softuni.springexam.service.model.UserRegisterServiceModel;
import softuni.springexam.web.model.UserLoginModel;
import softuni.springexam.web.model.UserRegisterModel;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    // constants must in another class but no time left :)
    private static final String REDIRECT_REGISTER = "redirect:/users/register";
    private static final String REDIRECT_LOGIN = "redirect:/users/login";
    private static final String REDIRECT_HOME = "redirect:/home";
    private final UserService userService;
    private final ModelMapper mapper;

    public UserController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/login")
    public ModelAndView getLogin(Model model, ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            modelAndView.setViewName(REDIRECT_HOME);
        } else {
            if (!model.containsAttribute("loginUser")) {
                model.addAttribute("loginUser", new UserLoginModel());
            }
            modelAndView.setViewName("login");
        }

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView getLoginConfirm(@Valid @ModelAttribute("loginUser") UserLoginModel loginUser,
                                        BindingResult bindingResult,
                                        HttpSession httpSession,
                                        RedirectAttributes redirectAttributes,
                                        ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginUser", loginUser);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginUser", bindingResult);
            modelAndView.setViewName(REDIRECT_LOGIN);
        }
        UserLoginServiceModel loginServiceModel = this.userService.login(this.mapper.map(loginUser, UserLoginServiceModel.class));
        if (loginServiceModel == null) {
            redirectAttributes.addFlashAttribute("loginUser", loginUser);
            redirectAttributes.addFlashAttribute("notFound", true);
            modelAndView.setViewName(REDIRECT_LOGIN);
        } else {
            httpSession.setAttribute("user", loginUser);
            modelAndView.setViewName(REDIRECT_HOME);

        }

        return modelAndView;
    }


    @GetMapping("/register")
    public ModelAndView getRegister(Model model, ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            modelAndView.setViewName(REDIRECT_HOME);
        } else {
            if (!model.containsAttribute("registerUser")) {
                model.addAttribute("registerUser", new UserRegisterModel());
            }
            modelAndView.setViewName("register");
        }

        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView getRegisterConfirm(@Valid @ModelAttribute("registerUser") UserRegisterModel registerUser,
                                           BindingResult bindingResult,
                                           HttpSession httpSession,
                                           RedirectAttributes redirectAttributes,
                                           ModelAndView modelAndView) {
        boolean matching = registerUser.getPassword().equals(registerUser.getConfirmPassword());

        if (bindingResult.hasErrors() || !matching) {

            redirectAttributes.addFlashAttribute("registerUser", registerUser);
            redirectAttributes.addFlashAttribute("matching", !matching);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerUser", bindingResult);
            modelAndView.setViewName(REDIRECT_REGISTER);
        } else {

            UserRegisterServiceModel serviceModel = this.mapper.map(registerUser, UserRegisterServiceModel.class);
            UserRegisterServiceModel register = this.userService.register(serviceModel);
            if (register == null) {
                redirectAttributes.addFlashAttribute("registerUser", registerUser);
                redirectAttributes.addFlashAttribute("found", true);
                modelAndView.setViewName(REDIRECT_REGISTER);

            } else {
                modelAndView.setViewName(REDIRECT_LOGIN);
            }
        }
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView getLogout(HttpSession httpSession, ModelAndView modelAndView) {
        httpSession.invalidate();
        modelAndView.setViewName("redirect:/");

        return modelAndView;
    }

}
