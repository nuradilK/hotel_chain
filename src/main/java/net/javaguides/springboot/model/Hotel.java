package net.javaguides.springboot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

	@JsonBackReference(value = "hotel-roomtype")
	@OneToMany(mappedBy = "hotel")
	private List<RoomType> roomTypes = new ArrayList<>();

	@JsonBackReference(value = "hotel-feature")
	@OneToMany(mappedBy = "hotel")
	private List<RoomType> features = new ArrayList<>();

	@JsonBackReference(value = "hotel-phone")
	@OneToMany(mappedBy = "hotel")
	private List<RoomType> phoneNumbers = new ArrayList<>();

	@JsonManagedReference(value = "hotel-employee")
	@OneToMany(mappedBy = "hotel")
	private List<Employee> employees = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "hotels_have_seasons",
			joinColumns = @JoinColumn(name = "hotelID", referencedColumnName = "hotelID"),
			inverseJoinColumns = @JoinColumn(name = "seasonId", referencedColumnName = "seasonId"))
	private List<Season> seasons = new ArrayList<>();

	public Hotel() {}

	public Hotel(String address, String name) {
		super();
		this.address = address;
		this.name = name;
	}

	public List<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
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
