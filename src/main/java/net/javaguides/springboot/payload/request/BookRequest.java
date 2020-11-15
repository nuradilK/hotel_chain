package net.javaguides.springboot.payload.request;

import net.javaguides.springboot.model.Room;
import net.javaguides.springboot.model.User;

import java.util.Date;

public class BookRequest {
    private long userID;
    private long roomID;
    private Date fromDate;
    private Date toDate;

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getRoomID() {
        return roomID;
    }

    public void setRoomID(long roomID) {
        this.roomID = roomID;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
