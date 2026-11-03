# Movie Ticket Booking System - Requirements and Design

## 1. Project details

- **Course:** CSC 1102: Structured and Object-Oriented Programming Practical Exam
- **Project:** Movie Ticket Booking System
- **Language and GUI:** Java with Swing
- **Group members:**
  - AKOL TINO BRIDGET
  - NANSUUNA VICTORIOUS
  - BUSINGYE FAVOUR BWESIGYE
  - LAKER EMMANUEL
  - LUBWAMA JAMAL
- **Group leader:** BUSINGYE FAVOUR BWESIGYE
- **Session:** Group 27 Day class
- **GitHub repository:** https://github.com/NassuunaVictorious/Movie-ticketing-system

## 2. Purpose and scope

The application will let a user browse a small in-memory list of movies and showtimes, choose available seats, and confirm a booking. It is a desktop learning project that demonstrates Java, Swing, classes and objects, arrays, methods, and flow control.

The first version will use sample data stored in arrays while the application is running. It will not include payment processing, user accounts, networking, or a database. These limits keep the project achievable and match the exam guidance that a small working application is preferable to an incomplete large one.

The group suggested **Prison Break**, **Breaking Bad**, and **Missing** as possible catalogue titles. `Missing` is a film, while `Prison Break` and `Breaking Bad` are television series. Since this project is a movie ticket booking system, use film titles in the final sample catalogue; replace the two series with films before adding sample data.

## 3. User requirements

1. The user can view the movies available in the application.
2. The user can select a movie and view its showtimes.
3. The user can select a showtime and view its seats.
4. The user can choose one or more seats that are still available.
5. The application checks that a movie, showtime, and at least one seat have been selected.
6. The application marks confirmed seats as booked so they cannot be booked again during the current run.
7. The application calculates the total using the showtime's ticket price and number of selected seats.
8. The user sees a booking summary containing the movie, showtime, seats, and total price.

## 4. Main booking flow

1. Start the application and load a small set of sample movies, showtimes, and seats.
2. Display the movies in the Swing window.
3. When the user selects a movie, display its showtimes.
4. When the user selects a showtime, display its seats and indicate which are available or booked.
5. Let the user select available seats.
6. On confirmation, validate the selections. If anything is missing or invalid, explain what needs to be corrected.
7. If valid, create and store a booking, mark its seats as booked, calculate the total, and display a summary.

## 5. Class design

The classes below divide the application into understandable responsibilities. Attributes should be `private`; constructors initialize valid objects; public getters provide needed information. Add setters only for values that need to change after construction. A seat's booking status changes through a focused `book()` method, while a movie title does not need to change during a booking.

| Class | Responsibility | Example data and methods |
|---|---|---|
| `Movie` | Represents one movie and its showtimes. | Private `title`, `genre`, `durationMinutes`, and fixed-capacity `ShowTime[]`; constructor; getters; `addShowTime` and indexed retrieval methods. |
| `ShowTime` | Represents one screening and its seats. | Private date/time, ticket price in whole shillings, and `Seat[]`; constructor creates the seats; getters; method to count available seats. |
| `Seat` | Represents one seat in a screening. | Private seat number and booked status; constructor; getters; `book()` changes the status once. |
| `Booking` | Records seats reserved for one movie showtime. | Private booking number, `Movie`, `ShowTime`, selected `Seat[]`, and total price; constructor; getters; total calculated from seat count and ticket price. |
| `TicketingSystem` | Holds the application's movie and booking collections and coordinates booking operations. | `Movie[] movies`, `Booking[] bookings`, and counts; methods to list/find movies and showtimes, validate and create a booking, and store it in the array. |
| `BookingFrame` | Builds the Swing interface and responds to user actions by calling `TicketingSystem` methods. | Swing components for movie, showtime, seat selection, confirmation, and booking summary. |
| `Main` | Starts the application and creates the initial objects. | `main` method; sample data setup; launches `BookingFrame` on Swing's event-dispatch thread. |

The exam requires at least three meaningful entity classes. `Movie`, `ShowTime`, `Seat`, and `Booking` meet this requirement; the other classes separate application coordination, interface, and startup responsibilities.

## 6. Object relationships

```text
Main
  -> creates TicketingSystem and launches BookingFrame

TicketingSystem
  -> stores Movie[]
  -> stores Booking[]

Movie
  -> contains ShowTime[]
       -> contains Seat[]

Booking
  -> refers to one Movie
  -> refers to one ShowTime
  -> refers to the booked Seat[]

BookingFrame
  -> uses TicketingSystem to display data and confirm bookings
```

## 7. Course concepts to demonstrate

- **Classes and objects:** create movie, showtime, seat, and booking objects.
- **Encapsulation:** keep fields private and expose appropriate public methods.
- **Constructors:** initialize objects with the information they need.
- **Access modifiers:** use `private` for internal state and `public` for operations other classes need.
- **Arrays:** store multiple movies, showtimes, seats, and bookings in arrays.
- **Conditionals:** validate selections and distinguish available from booked seats.
- **Loops:** display/search arrays, list seats, and count selected or available seats.
- **Swing:** let the user interact with the booking flow through a desktop GUI.

We will use flow-control constructs where they make sense. For example, `if/else` fits validation and seat status, while `for` loops fit traversing arrays. We should not add a `do-while` or `switch` just to include a construct if it does not serve a clear purpose.

## 8. Decisions still to confirm

- Which group member is the group leader?
- Is the group in the day or afternoon session?
- What GitHub repository link should appear in the report?
- Which two films should replace the TV series in the sample catalogue?
- What showtimes, seat count, and ticket price should the demonstration use?

## 9. Environment and implementation progress

- The development computer has Java and `javac` version 26.0.2.1 available.
- The project uses standard Java and Swing; no database, external library, or build tool is needed for the first version.
- The initial `Movie`, `ShowTime`, `Seat`, and `Booking` model classes are in `src/com/movieticket`.
- The Swing window and the `TicketingSystem` class that coordinates bookings are later implementation steps.
