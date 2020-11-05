package net.javaguides.springboot.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long room_id;

	@Column(name = "cleaned", nullable = true)
	private boolean cleaned = true;

	@Column(name = "guested", nullable = true)
	private boolean guested = false;

	@Column(name = "number", nullable = true)
	private int number;

	@Column(name = "destination", nullable = true)
	private String destination;

	@Column(name = "occupancy", nullable = true)
	private int occupancy;

	@Column(name = "floor", nullable = true)
	private int floor;

	@Temporal(TemporalType.DATE)
	private Date reservationInDate;

	@Temporal(TemporalType.DATE)
	private Date reservationOutDate;

	@ManyToOne
	@JoinColumn(name="roomType_id", nullable=false)
	private RoomType roomType;

	public Room() {}

	public Room(boolean cleaned, boolean guested, int number, int floor, Date reservationInDate,
				Date reservationOutDate) {
		super();
		this.cleaned = cleaned;
		this.guested = guested;
		this.number = number;
		this.floor = floor;
		this.reservationInDate = reservationInDate;
		this.reservationOutDate = reservationOutDate;
	}
	public long getId() {
		return room_id;
	}
	public void setId(long room_id) {
		this.room_id = room_id;
	}
	public boolean getCleaned() {
		return cleaned;
	}
	public void setCleaned(boolean cleaned) {
		this.cleaned = cleaned;
	}
	public boolean getGuested() {
		return guested;
	}
	public void setGuested(boolean guested) {
		this.guested = guested;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public Date getReservationInDate() {
		return reservationInDate;
	}
	public void setReservationInDate(Date reservationInDate) {
		this.reservationInDate = reservationInDate;
	}
	public Date getReservationOutDate() {
		return reservationOutDate;
	}
	public void setReservationOutDate(Date reservationOutDate) {
		this.reservationOutDate = reservationOutDate;
	}

}
