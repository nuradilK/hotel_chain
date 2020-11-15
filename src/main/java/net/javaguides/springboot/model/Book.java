package net.javaguides.springboot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

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

    @JsonBackReference(value = "room-book")
    @ManyToOne
    @JoinColumn(name = "roomID")
    private Room room;

    @JsonBackReference(value = "user-book")
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    public Book() {}

    public Book(Date fromDate, Date toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
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
