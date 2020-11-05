package net.javaguides.springboot.model;

import javax.persistence.*;
import java.util.ArrayList;

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hotelId")
    private int hotelId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel id", referencedColumnName = "hotelId")
    private ArrayList<RoomType> roomTypes;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rooms number", referencedColumnName = "roomId")
    private ArrayList<Room> rooms;

    public Book(int hotelId, ArrayList<RoomType> roomTypes, ArrayList<Room> rooms) {
        this.hotelId = hotelId;
        this.roomTypes = roomTypes;
        this.rooms = rooms;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public void setId(int bookId) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setRoomTypes(ArrayList<RoomType> roomTypes) {
        this.roomTypes = roomTypes;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public int getHotelId() {
        return hotelId;
    }

    public ArrayList<RoomType> getRoomTypes() {
        return roomTypes;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

}
