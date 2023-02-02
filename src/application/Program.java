package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Reservation;
import model.exceptions.DomainException;

public class Program {

	public static void main(String[] args) throws DomainException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		try {

			System.out.print("Room number: ");
			Integer roomNumber = sc.nextInt();

			System.out.print("Check-in date: (dd/mm/yyyy): ");
			LocalDate checkin = LocalDate.parse(sc.next(), formatter);

			System.out.print("Check-out date: (dd/mm/yyyy): ");
			LocalDate checkout = LocalDate.parse(sc.next(), formatter);

			Reservation reservation = new Reservation(roomNumber, checkin, checkout);

			System.out.println("\nReservation: " + reservation + "\n");
			
			System.out.println("Enter data to update the reservation: ");
			System.out.print("Check-in date: (dd/mm/yyyy): ");
			checkin = LocalDate.parse(sc.next(), formatter);

			System.out.print("Check-out date: (dd/mm/yyyy): ");
			checkout = LocalDate.parse(sc.next(), formatter);

			reservation.updateDates(checkin, checkout);

			System.out.println("\nReservation updated: " + reservation);
		}

		catch (InputMismatchException e) {
			System.out.println("Invalid input number!");
		}

		catch (DateTimeParseException e) {
			System.out.println("Incorrect date format!");
		}

		catch (DomainException e) {
			System.out.println("Error in reservation: " + e.getMessage());
		}

		catch (Exception e) {
			System.out.println("Unexpected error!");
		}

		sc.close();
	}
}
