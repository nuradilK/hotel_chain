package net.javaguides.springboot.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "hotels")
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long hotelID;

	@Column(name = "address")
	private String address;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "rating")
	private Integer rating;

	@JsonManagedReference(value = "hotel-roomtype")
	@OneToMany(mappedBy = "hotel")
	private List<RoomType> roomTypes = new ArrayList<>();

	@JsonManagedReference(value = "hotel-employee")
	@OneToMany(mappedBy = "hotel")
	private List<Employee> employees = new ArrayList<>();

	public Hotel() {}

	public Hotel(String address, String name) {
		super();
		this.address = address;
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
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

	public long getHotelID() { return hotelID; }

	public void setHotelID(long hotelID) { this.hotelID = hotelID; }

	public String getDescription() { return description; }

	public void setDescription(String description) { this.description = description; }

	public Integer getRating() { return rating; }

	public void setRating(Integer rating) { this.rating = rating; }

	public List<RoomType> getRoomTypes() { return roomTypes; }

	public void setRoomTypes(List<RoomType> roomTypes) { this.roomTypes = roomTypes; }
}
