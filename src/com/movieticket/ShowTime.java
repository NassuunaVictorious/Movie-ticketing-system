package com.movieticket;

/** Represents one screening and the seats available for that screening. */
public class ShowTime {
    private final String dateTime;
    private final int ticketPrice;
    private final Seat[] seats;

    public ShowTime(String dateTime, int ticketPrice, int numberOfSeats) {
        if (dateTime == null || dateTime.isBlank()) {
            throw new IllegalArgumentException("Showtime cannot be blank.");
        }
        if (ticketPrice < 0) {
            throw new IllegalArgumentException("Ticket price cannot be negative.");
        }
        if (numberOfSeats < 1) {
            throw new IllegalArgumentException("A showtime must have at least one seat.");
        }

        this.dateTime = dateTime;
        this.ticketPrice = ticketPrice;
        this.seats = new Seat[numberOfSeats];

        for (int index = 0; index < seats.length; index++) {
            seats[index] = new Seat("S" + (index + 1));
        }
    }

    public String getDateTime() {
        return dateTime;
    }

    /** Ticket price is a whole number of shillings to avoid decimal rounding. */
    public int getTicketPrice() {
        return ticketPrice;
    }

    public int getSeatCount() {
        return seats.length;
    }

    public Seat getSeatAt(int index) {
        if (index < 0 || index >= seats.length) {
            throw new IndexOutOfBoundsException("Seat number is outside this showtime.");
        }
        return seats[index];
    }

    public int getAvailableSeatCount() {
        int available = 0;
        for (Seat seat : seats) {
            if (!seat.isBooked()) {
                available++;
            }
        }
        return available;
    }

    @Override
    public String toString() {
        return dateTime + " - Ugx " + ticketPrice;
    }
}
