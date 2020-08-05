package examprep.softuni.web.controller;

import examprep.softuni.service.ItemService;
import examprep.softuni.service.model.bind.ItemAddServiceModel;
import examprep.softuni.web.model.ItemAddModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;
    private final ModelMapper mapper;

    public ItemController(ItemService itemService, ModelMapper mapper) {
        this.itemService = itemService;
        this.mapper = mapper;
    }

    @GetMapping("/add")
    public ModelAndView getAddItem(Model model, ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("redirect:/users/login");
        } else {
            if (!model.containsAttribute("item")) {
                model.addAttribute("item", new ItemAddModel());
            }
            modelAndView.setViewName("add-item");
        }

        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView getAddItemConfirm(@Valid @ModelAttribute("item") ItemAddModel item,
                                          BindingResult bindingResult,
                                          RedirectAttributes redirectAttributes,
                                          ModelAndView modelAndView,
                                          HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("redirect:/users/login");
        } else {
            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("item", item);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.item", bindingResult);
                modelAndView.setViewName("redirect:/items/add");
            } else {
                ItemAddServiceModel map = this.mapper.map(item, ItemAddServiceModel.class);
                this.itemService.addItem(map);
                modelAndView.setViewName("redirect:/home");
            }
        }

        return modelAndView;
    }

    @GetMapping("/details")
    public ModelAndView getDetails(@RequestParam("id") String id,
                                   ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("redirect:/users/login");
        } else {
            modelAndView.addObject("item", this.itemService.findById(id));
            modelAndView.setViewName("details-item");
        }
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("id") String id, ModelAndView model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            model.setViewName("redirect:/users/login");
        } else {
            this.itemService.deleteById(id);
            model.setViewName("redirect:/home");
        }

        return model;
}
}
