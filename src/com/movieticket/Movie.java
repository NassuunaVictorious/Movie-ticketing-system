package com.movieticket;

/** Represents a movie and its scheduled showtimes. */
public class Movie {
    private final String title;
    private final String genre;
    private final int durationMinutes;
    private final ShowTime[] showTimes;
    private int showTimeCount;

    public Movie(String title, String genre, int durationMinutes, int maximumShowTimes) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Movie title cannot be blank.");
        }
        if (genre == null || genre.isBlank()) {
            throw new IllegalArgumentException("Movie genre cannot be blank.");
        }
        if (durationMinutes < 1) {
            throw new IllegalArgumentException("Duration must be at least one minute.");
        }
        if (maximumShowTimes < 1) {
            throw new IllegalArgumentException("A movie must allow at least one showtime.");
        }

        this.title = title;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
        this.showTimes = new ShowTime[maximumShowTimes];
        this.showTimeCount = 0;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public int getShowTimeCount() {
        return showTimeCount;
    }

    public ShowTime getShowTimeAt(int index) {
        if (index < 0 || index >= showTimeCount) {
            throw new IndexOutOfBoundsException("Showtime index is outside this movie's schedule.");
        }
        return showTimes[index];
    }

    /** Adds a showtime if this movie's showtime array has room. */
    public boolean addShowTime(ShowTime showTime) {
        if (showTime == null || showTimeCount == showTimes.length) {
            return false;
        }
        showTimes[showTimeCount] = showTime;
        showTimeCount++;
        return true;
    }

    @Override
    public String toString() {
        return title + " (" + genre + ", " + durationMinutes + " min)";
    }
}
