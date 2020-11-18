package net.javaguides.springboot.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "roomfeatures")
public class RoomFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roomFeatureID;

    @Column(name = "name")
    private String name;

    @JsonManagedReference(value = "roomType-feature")
    @ManyToOne
    @JoinColumn(name = "roomtypeID", referencedColumnName = "roomtypeID")
    private RoomType roomType;

    public RoomFeature() {}

    public RoomFeature(String name) {
        super();
        this.name = name;
    }

    public long getFeatureID() {
        return roomFeatureID;
    }

    public void setFeatureID(long roomFeatureID) {
        this.roomFeatureID = roomFeatureID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

