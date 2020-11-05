package net.javaguides.springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "hotelId")
    private int hotelId;

    @Column(name = "roomType")
    private String roomType;

    @Column(name = "room")
    private int room;

    @Column(name = "userId")
    private int userId;

    public Book(Long id, int hotelId, String roomType, int room) {
        this.id = id;
        this.hotelId = hotelId;
        this.roomType = roomType;
        this.room = room;
    }

    public Book(int room, int userId) {
        this.room = room;
        this.userId = userId;
    }

    public Book() {}

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public void setId(int bookId) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getRoom() {
        return room;
    }
}
