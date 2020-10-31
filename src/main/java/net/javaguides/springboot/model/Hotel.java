package net.javaguides.springboot.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "hotels")
public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long hotel_id;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "hotelID", referencedColumnName = "hotel_id")
	List<RoomType> roomtypes = new ArrayList<>();
	
	public Hotel() {}
	
	public Hotel(String address, String name) {
		super();
		this.address = address;
		this.name = name;
	}
	public long getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(long hotel_id) {
		this.hotel_id = hotel_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<RoomType> getRoomtypes() {
		return roomtypes;
	}

	public void setRoomtypes(List<RoomType> roomtypes) {
		this.roomtypes = roomtypes;
	}
}
