package springworkshop.demo.web.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HeroDetailsViewModel {
    private String name;
    private Integer level;
    private Integer strength;
    private Integer stamina;
    private Integer attack;
    private Integer defence;

}
