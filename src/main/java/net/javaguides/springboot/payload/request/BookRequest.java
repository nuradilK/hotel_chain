package net.javaguides.springboot.payload.request;

import net.javaguides.springboot.model.Room;
import net.javaguides.springboot.model.RoomType;

import java.util.ArrayList;
import java.util.Date;

public class BookRequest {
    private int hotelId;
    private String roomType;
    private int room;
    private int userId;
    private Date fromDate;
    private Date toDate;

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public int getUserId() {
        return userId;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getRoom() {
        return room;
    }

    public int getHotelId(){
        return this.hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
}
