package net.javaguides.springboot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.DATE)
    private Date fromDate;

    @Temporal(TemporalType.DATE)
    private Date toDate;

    @Column(name = "bill")
    private double bill;

    @JsonBackReference(value = "room-book")
    @ManyToOne
    @JoinColumn(name = "roomID", referencedColumnName = "Id")
    private Room room;

    @JsonBackReference(value = "user-book")
    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "books_has_services",
            joinColumns = @JoinColumn(name = "bookid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "serviceid", referencedColumnName = "serviceID"))
    private List<Service> services = new ArrayList<>();

    public Book() {}

    public Book(Date fromDate, Date toDate, Room room, User user, double bill) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.room = room;
        this.user = user;
        this.bill = bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public double getBill() {
        return bill;
    }

    public void setId(long id) { this.id = id; }

    public void setFromDate(Date fromDate) { this.fromDate = fromDate; }

    public void setToDate(Date toDate) { this.toDate = toDate; }

    public Date getFromDate() { return fromDate; }

    public Date getToDate() { return toDate; }

    public Long getId() { return id; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Room getRoom() { return room; }

    public void setRoom(Room room) { this.room = room; }
}
