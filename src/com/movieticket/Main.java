package com.movieticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main extends JFrame {
    
    private JComboBox<Movie> movieDropdown;
    private JComboBox<ShowTime> showtimeDropdown;
    private JPanel seatGridPanel;
    private JButton bookButton;
    private JLabel statusLabel;
    
    private Movie[] catalogue;
    private List<Seat> selectedSeats;

    public Main() {
        super("Movie Ticket Booking System");
        
        // Group Members: AKOL TINO BRIDGET, NASSUUNA VICTORIOUS, BUSINGYE FAVOUR BWESIGYE, LAKER EMMANUEL, LUBWAMA JAMAL
        
        initializeData();
        setupUI();
        
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void initializeData() {
        catalogue = new Movie[2];
        
        Movie movie1 = new Movie("Black Panther: Wakanda Forever", "Action", 161, 2);
        movie1.addShowTime(new ShowTime("2026-11-10 18:00", 20000, 20));
        movie1.addShowTime(new ShowTime("2026-11-10 21:00", 25000, 20));
        
        Movie movie2 = new Movie("Spider-Man: Beyond the Spider-Verse", "Animation", 140, 1);
        movie2.addShowTime(new ShowTime("2026-11-11 15:00", 15000, 16));
        
        catalogue[0] = movie1;
        catalogue[1] = movie2;
        
        selectedSeats = new ArrayList<>();
    }

    private void setupUI() {
        setLayout(new BorderLayout(10, 10));
        
        // Top Panel: Selection Controls
        JPanel topPanel = new JPanel(new FlowLayout());
        
        movieDropdown = new JComboBox<>(catalogue);
        showtimeDropdown = new JComboBox<>();
        
        movieDropdown.addActionListener(e -> updateShowtimes());
        showtimeDropdown.addActionListener(e -> renderSeats());
        
        topPanel.add(new JLabel("Select Movie:"));
        topPanel.add(movieDropdown);
        topPanel.add(new JLabel("Select Showtime:"));
        topPanel.add(showtimeDropdown);
        
        add(topPanel, BorderLayout.NORTH);
        
        // Center Panel: Seat Grid
        seatGridPanel = new JPanel();
        add(new JScrollPane(seatGridPanel), BorderLayout.CENTER);
        
        // Bottom Panel: Booking & Status
        JPanel bottomPanel = new JPanel(new BorderLayout());
        statusLabel = new JLabel("Please select a movie and showtime to begin.");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        bookButton = new JButton("Book Selected Seats");
        bookButton.setEnabled(false);
        bookButton.addActionListener(new BookAction());
        
        bottomPanel.add(statusLabel, BorderLayout.CENTER);
        bottomPanel.add(bookButton, BorderLayout.EAST);
        
        add(bottomPanel, BorderLayout.SOUTH);
        
        // Initialize first state
        updateShowtimes();
    }
    
    private void updateShowtimes() {
        Movie selectedMovie = (Movie) movieDropdown.getSelectedItem();
        showtimeDropdown.removeAllItems();
        if (selectedMovie != null) {
            for (ShowTime st : selectedMovie.getAvailableShowTimes()) {
                showtimeDropdown.addItem(st);
            }
        }
        renderSeats();
    }
    
    private void renderSeats() {
        seatGridPanel.removeAll();
        selectedSeats.clear();
        bookButton.setEnabled(false);
        
        ShowTime selectedShowtime = (ShowTime) showtimeDropdown.getSelectedItem();
        
        if (selectedShowtime != null) {
            Seat[] seats = selectedShowtime.getAllSeats();
            
            // Calculate a rough grid (e.g., 4 columns)
            int cols = 4;
            int rows = (int) Math.ceil((double) seats.length / cols);
            seatGridPanel.setLayout(new GridLayout(rows, cols, 5, 5));
            seatGridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            for (Seat seat : seats) {
                JToggleButton seatBtn = new JToggleButton(seat.getSeatNumber());
                if (seat.isBooked()) {
                    seatBtn.setEnabled(false);
                    seatBtn.setText(seat.getSeatNumber() + " (X)");
                    seatBtn.setBackground(Color.RED);
                } else {
                    seatBtn.setBackground(Color.GREEN);
                    seatBtn.addActionListener(e -> {
                        if (seatBtn.isSelected()) {
                            selectedSeats.add(seat);
                        } else {
                            selectedSeats.remove(seat);
                        }
                        updateStatus();
                    });
                }
                seatGridPanel.add(seatBtn);
            }
        }
        seatGridPanel.revalidate();
        seatGridPanel.repaint();
        updateStatus();
    }
    
    private void updateStatus() {
        ShowTime selectedShowtime = (ShowTime) showtimeDropdown.getSelectedItem();
        if (selectedShowtime == null) return;
        
        if (selectedSeats.isEmpty()) {
            statusLabel.setText("0 seats selected.");
            bookButton.setEnabled(false);
        } else {
            int total = selectedSeats.size() * selectedShowtime.getTicketPrice();
            statusLabel.setText(selectedSeats.size() + " seat(s) selected. Total: Ugx " + total);
            bookButton.setEnabled(true);
        }
    }

    private class BookAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Movie currentMovie = (Movie) movieDropdown.getSelectedItem();
            ShowTime currentShowtime = (ShowTime) showtimeDropdown.getSelectedItem();
            
            if (currentMovie == null || currentShowtime == null || selectedSeats.isEmpty()) {
                return;
            }
            
            Seat[] seatsToBook = selectedSeats.toArray(new Seat[0]);
            
            for (Seat s : seatsToBook) {
                s.book(); 
            }
            
            String bookingRef = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            Booking newBooking = new Booking(bookingRef, currentMovie, currentShowtime, seatsToBook);
            
            JOptionPane.showMessageDialog(Main.this, 
                "Booking Successful!\n" +
                "Reference: " + newBooking.getBookingNumber() + "\n" +
                "Movie: " + currentMovie.getTitle() + "\n" +
                "Total Paid: Ugx " + newBooking.getTotalPrice(),
                "Success", JOptionPane.INFORMATION_MESSAGE);
                
            renderSeats(); 
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}