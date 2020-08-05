package softuni.springexam.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Data
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity {
    private CategoryName name;
    private String description;

    public Category() {
    }
    public Category(CategoryName name, String description) {
        this.name = name;
        this.description = description;
    }

    @Column(name = "category", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
