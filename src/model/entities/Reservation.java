package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import model.exceptions.DomainException;

public class Reservation {

	private Integer roomNumber;
	private LocalDate checkin;
	private LocalDate checkout;

	public Reservation() {

	}

	public Reservation(Integer roomNumber, LocalDate checkin, LocalDate checkout) throws DomainException {

		if (checkout.isBefore(checkin)) {
			throw new DomainException("Check-out date must be after check-in date");
		}

		this.roomNumber = roomNumber;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDate getCheckin() {
		return checkin;
	}

	public LocalDate getCheckout() {
		return checkout;
	}

	public Integer duration() {
		return (int) ChronoUnit.DAYS.between(checkin, checkout);
	}

	public void updateDates(LocalDate checkin, LocalDate checkout) throws DomainException {

		if (checkin.isBefore(LocalDate.now()) || checkout.isBefore(LocalDate.now())) {
			throw new DomainException("Reservation dates for update must be future dates");
		}

		if (checkout.isBefore(checkin)) {
			throw new DomainException("Check-out date must be after check-in date");
		}

		this.checkin = checkin;
		this.checkout = checkout;
	}

	@Override
	public String toString() {
		return "Room " + roomNumber +
				", check-in: " + checkin.format(DateTimeFormatter.ofPattern("dd/MM/yyy")) +
				", check-out: " + checkout.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + 
				", " + duration() + " nights";
	}

}
