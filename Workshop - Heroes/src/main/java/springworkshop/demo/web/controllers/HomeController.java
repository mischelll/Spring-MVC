package springworkshop.demo.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import springworkshop.demo.service.UserService;
import springworkshop.demo.web.models.UserProfileViewModel;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class HomeController extends BaseController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ModelAndView getIndex(){
        return new ModelAndView("home/index");
    }


    @GetMapping("/home")
    public ModelAndView getHome(Principal principal, HttpSession session){
        UserProfileViewModel map = this.modelMapper.map(
                this.userService.findByUsername(principal.getName()), UserProfileViewModel.class);
       session.setAttribute("user",map);

        if (map.getHero() != null){
            return new ModelAndView("home/home-with-created-hero");
        }
        return new ModelAndView("home/home-hero-not-created");
    }
}
