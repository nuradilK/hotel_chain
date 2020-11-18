package net.javaguides.springboot.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "userCategories")
public class UserCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userCategoryID;

    @Column(name = "name")
    private String name;

    @Column(name = "coefficient")
    private double coefficient;

    @JsonManagedReference(value = "userCategory-user")
    @OneToMany(mappedBy = "userCategory")
    private List<User> users = new ArrayList<>();

    public UserCategory(String name, double coefficient) {
        this.name = name;
        this.coefficient = coefficient;
    }

    public UserCategory(String name, double coefficient, List<User> users) {
        super();
        this.name = name;
        this.coefficient = coefficient;
        this.users = users;
    }

    public UserCategory() {}

    public long getUserCategoryID() {
        return userCategoryID;
    }

    public void setUserCategoryID(long userCategoryID) {
        this.userCategoryID = userCategoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
