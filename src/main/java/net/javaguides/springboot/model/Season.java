package net.javaguides.springboot.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "seasons")
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seasonId;

    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "coefficient")
    private double coefficient;

    public Season() {
    }

    public Season(String name, Date startDate, Date endDate, double coefficient) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coefficient = coefficient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFromDate() {
        return endDate;
    }

    public void setFromDate(Date fromDate) {
        this.endDate = fromDate;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
}
