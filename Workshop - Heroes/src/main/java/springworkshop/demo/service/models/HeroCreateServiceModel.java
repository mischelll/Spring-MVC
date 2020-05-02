package springworkshop.demo.service.models;

public class HeroCreateServiceModel {
    private String name;
    private String gender;

    public HeroCreateServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
