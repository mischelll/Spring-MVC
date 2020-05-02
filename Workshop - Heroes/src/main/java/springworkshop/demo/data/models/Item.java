package springworkshop.demo.data.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "items")
public class Item extends BaseEntity {
    private String name;
    private Slot slot;
    private Integer stamina;
    private Integer strength;
    private Integer attack;
    private Integer defence;
    private List<Hero> heroes;

    public Item() {
    }

    @ManyToMany(mappedBy = "items")
    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    @Enumerated(EnumType.STRING)
    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    @Column(nullable = false)
    public Integer getStamina() {
        return stamina;
    }

    public void setStamina(Integer stamina) {
        this.stamina = stamina;
    }

    @Column(nullable = false)
    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    @Column(nullable = false)
    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    @Column(nullable = false)
    public Integer getDefence() {
        return defence;
    }

    public void setDefence(Integer defence) {
        this.defence = defence;
    }
}
