package net.javaguides.springboot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private String position;

    @Column(name = "hours")
    private int hours;

    @Column(name = "salary")
    private int salary;

    @JsonBackReference(value = "hotel-employee")
    @ManyToOne
    @JoinColumn(name = "hotelID", referencedColumnName = "hotelID")
    private Hotel hotel;

    @JsonManagedReference(value = "supervisor-subordinate")
    @OneToMany(mappedBy="supervisor")
    private Set<Employee> subordinates = new HashSet<>();

    @JsonBackReference(value = "supervisor-subordinate")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name="supervisorID", nullable = true)
    private Employee supervisor;

    public Employee(String name, String position, int hours, int salary, Hotel hotel) {
        this.name = name;
        this.position = position;
        this.hours = hours;
        this.salary = salary;
        this.hotel = hotel;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
