package net.javaguides.springboot.payload.request;

import net.javaguides.springboot.model.Room;
import net.javaguides.springboot.model.RoomType;

import java.util.ArrayList;

public class BookRequest {
    private int hotelId;
    private String roomType;
    private int room;
    private int userId;

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
