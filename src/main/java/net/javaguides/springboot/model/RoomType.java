package net.javaguides.springboot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "roomtypes")
public class RoomType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long roomtypeID;

	@Column(name = "type")
	private String type;

	@Column(name = "capacity")
	private Integer capacity;

	@Column(name = "size")
	private long size;

	@Column(name = "price_M")
	private Integer price_M;

	@Column(name = "price_T")
	private Integer price_T;

	@Column(name = "price_W")
	private Integer price_W;

	@Column(name = "price_R")
	private Integer price_R;

	@Column(name = "price_F")
	private Integer price_F;

	@Column(name = "price_St")
	private Integer price_St;

	@Column(name = "price_Sn")
	private Integer price_Sn;

	@JsonBackReference(value = "roomtype-room")
	@OneToMany(mappedBy = "roomType")
	private List<Room> rooms = new ArrayList<>();

	@JsonBackReference(value = "roomType-feature")
	@OneToMany(mappedBy = "roomType")
	private List<RoomFeature> roomFeatures = new ArrayList<>();

	@JsonManagedReference(value = "hotel-roomtype")
	@ManyToOne
	@JoinColumn(name = "hotelID", referencedColumnName = "hotelID")
	private Hotel hotel;

	public RoomType() {}

	public RoomType(String type, Integer capacity, long size, Integer price_M,
					Integer price_T, Integer price_W, Integer price_R, Integer price_F,
					Integer price_St, Integer price_Sn) {
		super();
		this.type = type;
		this.capacity = capacity;
		this.size = size;
		this.price_M = price_M;
		this.price_T = price_T;
		this.price_W = price_W;
		this.price_R = price_R;
		this.price_F = price_F;
		this.price_St = price_St;
		this.price_Sn = price_Sn;
	}

	public long getRoomtypeID() {
		return roomtypeID;
	}

	public void setRoomtypeID(long roomtypeID) {
		this.roomtypeID = roomtypeID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Integer getPrice_M() {
		return price_M;
	}

	public void setPrice_M(Integer price_M) {
		this.price_M = price_M;
	}

	public Integer getPrice_T() {
		return price_T;
	}

	public void setPrice_T(Integer price_T) {
		this.price_T = price_T;
	}

	public Integer getPrice_W() {
		return price_W;
	}

	public void setPrice_W(Integer price_W) {
		this.price_W = price_W;
	}

	public Integer getPrice_R() {
		return price_R;
	}

	public void setPrice_R(Integer price_R) {
		this.price_R = price_R;
	}

	public Integer getPrice_F() {
		return price_F;
	}

	public void setPrice_F(Integer price_F) {
		this.price_F = price_F;
	}

	public Integer getPrice_St() {
		return price_St;
	}

	public void setPrice_St(Integer price_St) {
		this.price_St = price_St;
	}

	public Integer getPrice_Sn() {
		return price_Sn;
	}

	public void setPrice_Sn(Integer price_Sn) {
		this.price_Sn = price_Sn;
	}

	public List<Room> getRooms() { return rooms; }

	public void setRooms(List<Room> rooms) { this.rooms = rooms; }

	public Hotel getHotel() { return hotel; }

	public void setHotel(Hotel hotel) { this.hotel = hotel; }
}
