package com.movieticket;

/** Represents one seat in a particular showtime. */
public class Seat {
    private final String seatNumber;
    private boolean booked;

    public Seat(String seatNumber) {
        if (seatNumber == null || seatNumber.isBlank()) {
            throw new IllegalArgumentException("Seat number cannot be blank.");
        }
        this.seatNumber = seatNumber;
        this.booked = false;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isBooked() {
        return booked;
    }

    /** Marks this seat as booked once; returns false if it was already booked. */
    public boolean book() {
        if (booked) {
            return false;
        }
        booked = true;
        return true;
    }

    @Override
    public String toString() {
        return seatNumber + (booked ? " (Booked)" : " (Available)");
    }
}