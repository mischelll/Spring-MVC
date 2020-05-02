package springworkshop.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springworkshop.demo.data.models.Gender;
import springworkshop.demo.data.models.Hero;
import springworkshop.demo.data.models.User;
import springworkshop.demo.data.repositories.HeroRepository;
import springworkshop.demo.data.repositories.UserRepository;
import springworkshop.demo.errors.HeroNotFoundException;
import springworkshop.demo.service.HeroService;
import springworkshop.demo.service.models.HeroCreateServiceModel;
import springworkshop.demo.service.models.HeroDetailsServiceModel;

import java.util.Optional;

@Service
public class HeroServiceImpl implements HeroService {
    private final HeroRepository heroRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public HeroServiceImpl(HeroRepository heroRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.heroRepository = heroRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public boolean createHero(HeroCreateServiceModel hero, String username) {
        if (this.heroRepository.findByName(hero.getName()).isPresent()) {
            return false;
        }

        Hero map = new Hero();
        map.setName(hero.getName());
        map.setGender(Gender.valueOf(hero.getGender()));
        map.setAttack(2);
        map.setDefence(5);
        map.setLevel(1);
        map.setStamina(6);
        map.setStrength(5);

        User user = this.userRepository.findByUsername(username);
        map.setUser(user);
        this.heroRepository.saveAndFlush(map);
        return true;
    }

    @Override
    public HeroDetailsServiceModel findByName(String heroName) {
        Optional<Hero> byUsername = this.heroRepository.findByName(heroName);

        if (byUsername.isEmpty()){
            throw new HeroNotFoundException("Hero not found!");
        }
        Hero hero = byUsername.get();
        HeroDetailsServiceModel map = this.modelMapper.map(hero, HeroDetailsServiceModel.class);

        return map;
    }
}
