package net.javaguides.springboot.payload.request;

import net.javaguides.springboot.model.Room;
import net.javaguides.springboot.model.RoomType;

import java.util.ArrayList;

public class BookRequest {
    private int hotelId;
    private ArrayList<RoomType> roomTypes;
    private ArrayList<Room> rooms;

    public int getHotelId(){
        return this.hotelId;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<RoomType> getRoomTypes() {
        return roomTypes;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public void setRoomTypes(ArrayList<RoomType> roomTypes) {
        this.roomTypes = roomTypes;
    }
}
