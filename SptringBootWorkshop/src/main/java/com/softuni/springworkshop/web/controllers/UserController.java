package com.softuni.springworkshop.web.controllers;

import com.softuni.springworkshop.service.UserService;
import com.softuni.springworkshop.service.models.UserRegisterServiceModel;
import com.softuni.springworkshop.web.models.UserRegisterModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UserService userService;
    private final ModelMapper mapper;

    @Autowired
    public UserController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/login")
    public ModelAndView getLogin(HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            /* The user is logged in :) */
            return new ModelAndView("redirect:/home");

        }

        return new ModelAndView("login");
    }


    @GetMapping("/register")
    public ModelAndView getRegister(@ModelAttribute(name = "user") @Valid UserRegisterModel user,
                                    BindingResult bindingResult, ModelAndView model) {
        model.addObject("user", user);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            /* The user is logged in :) */
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView getRegisterConfirm(@ModelAttribute(name = "user") @Valid UserRegisterModel user,
                                           BindingResult bindingResult,
                                           HttpSession httpSession,
                                           ModelAndView modelAndView,
                                           RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", user);
            modelAndView.setViewName("redirect:/users/register");
        } else {
            UserRegisterServiceModel userRegisterServiceModel = this.userService.register(this.mapper.map(user, UserRegisterServiceModel.class));
            modelAndView.setViewName("redirect:/users/login");
        }
//        if (!this.userService.register(this.mapper.map(user, UserRegisterServiceModel.class))) {
//            return new ModelAndView("register");
//
//        }
        return modelAndView;
    }
}

