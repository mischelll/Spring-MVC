package examprep.softuni.web.controller;

import examprep.softuni.service.UserService;
import examprep.softuni.service.model.bind.UserLoginServiceModel;
import examprep.softuni.service.model.bind.UserRegisterServiceModel;
import examprep.softuni.web.model.UserLoginModel;
import examprep.softuni.web.model.UserRegisterModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final String REDIRECT_REGISTER = "redirect:/users/register";
    private static final String REDIRECT_LOGIN = "redirect:/users/login";
    private final UserService userService;
    private final ModelMapper mapper;

    public UserController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/login")
    public ModelAndView getLogin(Model model, ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            modelAndView.setViewName("redirect:/home");
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
            modelAndView.setViewName("redirect:/home");

        }


        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView getRegister(Model model, ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            modelAndView.setViewName("redirect:/home");
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

        if (bindingResult.hasErrors() || !registerUser.getPassword().equals(registerUser.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("registerUser", registerUser);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerUser", bindingResult);
            modelAndView.setViewName(REDIRECT_REGISTER);
        } else {
            UserRegisterServiceModel serviceModel = this.mapper.map(registerUser, UserRegisterServiceModel.class);
            this.userService.register(serviceModel);

            modelAndView.setViewName(REDIRECT_LOGIN);

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
