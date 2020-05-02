package springworkshop.demo.service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HeroDetailsServiceModel {
    private String name;
    private Integer level;
    private Integer strength;
    private Integer stamina;
    private Integer attack;
    private Integer defence;

}
