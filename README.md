# Movie Ticket Booking System
CSC 1102 Structured and Object-Oriented Programming practical exam.

## Development ground rules
- it Uses Java and Swing,
- Store movies, showtimes, seats, and bookings in arrays while the program runs;
- Keep each class focused on one kind of information or responsibility.
- Keep fields private. Add setters only when a value should change; use a focused method for state changes such as booking a seat.

## Environment

The project uses standard Java and Swing; no third-party libraries or build tool are needed. A JDK is required because it includes both `java` and `javac`. The development computer currently reports Java 26.0.2.1.

From the project root in PowerShell, compile all source files and run the entry point:

```powershell
javac -d out src/com/movieticket/*.java
java -cp out com.movieticket.Main
```

The `out` folder contains generated class files and is excluded from Git.

## Current foundation

- `Movie` stores a movie's details and a fixed-capacity array of showtimes.
- `ShowTime` stores a screening's date/time, ticket price, and seats.
- `Seat` stores its seat number and booking status.
- `Booking` stores a confirmed booking's movie, showtime, seats, and total.
- `Main` is the application entry point. The Swing interface and booking coordinator will be added in later steps.

