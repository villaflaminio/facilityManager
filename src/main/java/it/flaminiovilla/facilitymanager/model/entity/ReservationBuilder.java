package it.flaminiovilla.facilitymanager.model.entity;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public final class ReservationBuilder {
    private Long id;
    private Structure structure;
    private @NotNull LocalDate arrival;
    private @NotNull LocalDate departure;
    private @NotNull LocalTime checkIn;
    private @NotNull LocalTime checkOut;
    private @NotNull Integer guests;
    private @NotNull String guestName;
    private @NotNull String guestSurname;

    private ReservationBuilder() {
    }

    public static ReservationBuilder builder() {
        return new ReservationBuilder();
    }

    public ReservationBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ReservationBuilder structure(Structure structure) {
        this.structure = structure;
        return this;
    }

    public ReservationBuilder arrival(LocalDate arrival) {
        this.arrival = arrival;
        return this;
    }

    public ReservationBuilder departure(LocalDate departure) {
        this.departure = departure;
        return this;
    }

    public ReservationBuilder checkIn(LocalTime checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public ReservationBuilder checkOut(LocalTime checkOut) {
        this.checkOut = checkOut;
        return this;
    }

    public ReservationBuilder guests(Integer guests) {
        this.guests = guests;
        return this;
    }

    public ReservationBuilder guestName(String guestName) {
        this.guestName = guestName;
        return this;
    }

    public ReservationBuilder guestSurname(String guestSurname) {
        this.guestSurname = guestSurname;
        return this;
    }

    public Reservation build() {
        Reservation reservation = new Reservation();
        reservation.setId(id);
        reservation.setStructure(structure);
        reservation.setArrival(arrival);
        reservation.setDeparture(departure);
        reservation.setCheckIn(checkIn);
        reservation.setCheckOut(checkOut);
        reservation.setGuests(guests);
        reservation.setGuestName(guestName);
        reservation.setGuestSurname(guestSurname);
        return reservation;
    }
}
