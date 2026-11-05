package com.movieticket;

/** Records seats reserved for one movie showtime. */
public class Booking {
    private final String bookingNumber;
    private final Movie movie;
    private final ShowTime showTime;
    private final Seat[] seats;
    private final int totalPrice;

    public Booking(String bookingNumber, Movie movie, ShowTime showTime, Seat[] selectedSeats) {
        if (bookingNumber == null || bookingNumber.isBlank()) {
            throw new IllegalArgumentException("Booking number cannot be blank.");
        }
        if (movie == null || showTime == null) {
            throw new IllegalArgumentException("A booking needs a movie and a showtime.");
        }
        if (selectedSeats == null || selectedSeats.length == 0) {
            throw new IllegalArgumentException("A booking must contain at least one seat.");
        }

        this.bookingNumber = bookingNumber;
        this.movie = movie;
        this.showTime = showTime;
        this.seats = new Seat[selectedSeats.length];

        for (int index = 0; index < selectedSeats.length; index++) {
            if (selectedSeats[index] == null) {
                throw new IllegalArgumentException("Selected seats cannot contain null.");
            }
            this.seats[index] = selectedSeats[index];
        }

        this.totalPrice = showTime.getTicketPrice() * seats.length;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public Movie getMovie() {
        return movie;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public int getSeatCount() {
        return seats.length;
    }

    public Seat getSeatAt(int index) {
        if (index < 0 || index >= seats.length) {
            throw new IndexOutOfBoundsException("Seat index is outside this booking.");
        }
        return seats[index];
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}