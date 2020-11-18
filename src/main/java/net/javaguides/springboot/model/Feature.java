package net.javaguides.springboot.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "features")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long featureID;

    @Column(name = "name")
    private String name;

    @JsonManagedReference(value = "hotel-feature")
    @ManyToOne
    @JoinColumn(name = "hotelID", referencedColumnName = "hotelID")
    private Hotel hotel;

    public Feature() {}

    public Feature(String name) {
        super();
        this.name = name;
    }

    public long getFeatureID() {
        return featureID;
    }

    public void setFeatureID(long featureID) {
        this.featureID = featureID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
