package springworkshop.demo.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springworkshop.demo.data.models.User;
import springworkshop.demo.errors.HeroNotFoundException;
import springworkshop.demo.service.HeroService;
import springworkshop.demo.service.models.HeroCreateServiceModel;
import springworkshop.demo.service.models.HeroDetailsServiceModel;
import springworkshop.demo.web.models.HeroCreateModel;
import springworkshop.demo.web.models.HeroDetailsViewModel;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/heroes")
public class HeroController extends BaseController {
    private final ModelMapper modelMapper;
    private final HeroService heroService;

    @Autowired
    public HeroController(ModelMapper modelMapper, HeroService heroService) {
        this.modelMapper = modelMapper;
        this.heroService = heroService;
    }


    @GetMapping("/create")
    public ModelAndView getCreate(HttpSession session) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //should create a model, no direct access to User.class!!!
        if (principal.getHero() != null) {
            return super.redirect("/home");
        }
        return super.view("hero/create-hero");

    }

    @PostMapping("/create")
    public ModelAndView getCreateConfirm(@ModelAttribute HeroCreateModel hero, Principal principal) {


        HeroCreateServiceModel createdHero = this.modelMapper.map(hero, HeroCreateServiceModel.class);
        this.heroService
                .createHero(createdHero
                        , principal.getName());
        return super.redirect("/home");

    }

    @GetMapping("/details/{heroName}")
    public ModelAndView getHeroDetails(@PathVariable String heroName, ModelAndView model) {
        HeroDetailsServiceModel byName = this.heroService.findByName(heroName);
        HeroDetailsViewModel viewModel = this.modelMapper.map(byName, HeroDetailsViewModel.class);

        model.addObject("hero", viewModel);
        model.setViewName("hero/heroDetails");

        return model;

    }

    @ExceptionHandler(HeroNotFoundException.class)
    public ModelAndView handleException(HeroNotFoundException ex) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("message", ex.getMessage());

        return model;
    }
}
