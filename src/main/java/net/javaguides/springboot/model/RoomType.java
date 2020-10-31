package net.javaguides.springboot.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "roomtypes")
public class RoomType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long roomType_id;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "capacity")
	private int capacity;
	
	@Column(name = "size")
	private long size;
	
	@Column(name = "price_M")
	private String price_M;
	
	@Column(name = "price_T")
	private String price_T;
	
	@Column(name = "price_W")
	private String price_W;
	
	@Column(name = "price_R")
	private String price_R;
	
	@Column(name = "price_F")
	private String price_F;
	
	@Column(name = "price_St")
	private String price_St;
	
	@Column(name = "price_Sn")
	private String price_Sn;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "roomTypeID", referencedColumnName = "roomType_id")
	List<Room> rooms = new ArrayList<>();
	
	public RoomType() {}
	
	public RoomType(String type, int capacity, long size, String price_M, String price_T, String price_W,
			String price_R, String price_F, String price_St, String price_Sn) {
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
	public long getRoomType_id() {
		return roomType_id;
	}
	public void setRoomType_id(long roomType_id) {
		this.roomType_id = roomType_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getPrice_M() {
		return price_M;
	}
	public void setPrice_M(String price_M) {
		this.price_M = price_M;
	}
	public String getPrice_T() {
		return price_T;
	}
	public void setPrice_T(String price_T) {
		this.price_T = price_T;
	}
	public String getPrice_W() {
		return price_W;
	}
	public void setPrice_W(String price_W) {
		this.price_W = price_W;
	}
	public String getPrice_R() {
		return price_R;
	}
	public void setPrice_R(String price_R) {
		this.price_R = price_R;
	}
	public String getPrice_F() {
		return price_F;
	}
	public void setPrice_F(String price_F) {
		this.price_F = price_F;
	}
	public String getPrice_St() {
		return price_St;
	}
	public void setPrice_St(String price_St) {
		this.price_St = price_St;
	}
	public String getPrice_Sn() {
		return price_Sn;
	}
	public void setPrice_Sn(String price_Sn) {
		this.price_Sn = price_Sn;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
}
