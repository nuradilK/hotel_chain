package net.javaguides.springboot.payload.request;

import net.javaguides.springboot.model.Room;
import net.javaguides.springboot.model.User;

import java.util.Date;

public class BookRequest {
    private User user;
    private Room room;
    private Date fromDate;
    private Date toDate;

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Room getRoom() { return room; }

    public void setRoom(Room room) { this.room = room; }
}
