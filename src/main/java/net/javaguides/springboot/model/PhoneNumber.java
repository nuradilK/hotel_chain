package net.javaguides.springboot.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "phonenumbers")
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long phoneNumberID;

    @Column(name = "name")
    private String phone;

    @JsonManagedReference(value = "hotel-phone")
    @ManyToOne
    @JoinColumn(name = "hotelID", referencedColumnName = "hotelID")
    private Hotel hotel;

    public PhoneNumber() {}

    public PhoneNumber(String phone) {
        super();
        this.phone = phone;
    }

    public long getFeatureID() {
        return phoneNumberID;
    }

    public void setFeatureID(long phoneNumberID) {
        this.phoneNumberID = phoneNumberID;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String phone) {
        this.phone = phone;
    }
}