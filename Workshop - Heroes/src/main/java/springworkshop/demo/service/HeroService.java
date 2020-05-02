package springworkshop.demo.service;

import springworkshop.demo.service.models.HeroCreateServiceModel;
import springworkshop.demo.service.models.HeroDetailsServiceModel;

public interface HeroService {
    boolean createHero(HeroCreateServiceModel hero, String username);

    HeroDetailsServiceModel findByName(String heroName);
}
