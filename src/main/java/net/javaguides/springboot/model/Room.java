package net.javaguides.springboot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

	@Type(type="boolean")
	private Boolean cleaned;

	@Type(type="boolean")
	private Boolean guested;

	@Column(name = "number")
	private Integer number;

	@Column(name = "destination")
	private String destination;

	@Column(name = "occupancy")
	private Integer occupancy;

	@Column(name = "floor")
	private Integer floor;

	@JsonBackReference(value = "roomtype-room")
	@ManyToOne
	@JoinColumn(name = "roomtypeID", referencedColumnName = "roomtypeID")
	private RoomType roomType;

	@JsonManagedReference(value = "room-book")
	@OneToMany(mappedBy = "room")
	private List<Book> books = new ArrayList<>();

	public Room() {}

	public Room(Boolean cleaned, Boolean guested, int number,
				String destination, int occupancy, int floor) {
		super();
		this.cleaned = cleaned;
		this.guested = guested;
		this.number = number;
		this.destination = destination;
		this.occupancy = occupancy;
		this.floor = floor;
	}

	public long getId() { return Id; }

	public void setId(long roomID) { this.Id = roomID; }

	public boolean isCleaned() { return cleaned; }

	public void setCleaned(boolean cleaned) { this.cleaned = cleaned; }

	public boolean isGuested() { return guested; }

	public void setGuested(boolean guested) { this.guested = guested; }

	public Integer getNumber() { return number; }

	public void setNumber(Integer number) { this.number = number; }

	public String getDestination() { return destination; }

	public void setDestination(String destination) { this.destination = destination; }

	public int getOccupancy() { return occupancy; }

	public void setOccupancy(int occupancy) { this.occupancy = occupancy; }

	public Integer getFloor() { return floor; }

	public void setFloor(Integer floor) { this.floor = floor; }

	public RoomType getRoomType() { return roomType; }

	public void setRoomType(RoomType roomType) { this.roomType = roomType; }

	public List<Book> getBooks() { return books; }

	public void setBooks(List<Book> books) { this.books = books; }

}
