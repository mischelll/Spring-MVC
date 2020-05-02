package springworkshop.demo.data.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "heroes")
public class Hero extends BaseEntity {
    private String name;
    private Gender gender;
    private Integer level;
    private Integer stamina;
    private Integer strength;
    private Integer attack;
    private Integer defence;
    private List<Item> items;
    private User user;

    public Hero() {
    }

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "hero_items",
            joinColumns = @JoinColumn(name = "hero_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    @Enumerated(EnumType.STRING)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column(nullable = false)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
