import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CinemaBookingSystem extends JFrame {
    private static final int ROWS = 8;
    private static final int COLS = 10;
    private SeatButton[][] seats = new SeatButton[ROWS][COLS];
    private List<String> bookedSeats = new ArrayList<>();
    private JPanel seatPanel;
    private JPanel controlPanel;
    private JLabel statusLabel;

    public CinemaBookingSystem() {
        setTitle("Cinema Seat Booking System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(30, 30, 50));

        // Create seat panel with a title
        JPanel centerPanel = new JPanel(new BorderLayout(5, 10));
        centerPanel.setBackground(new Color(30, 30, 50));
        
        JLabel seatPanelTitle = new JLabel("Select Your Seats", JLabel.CENTER);
        seatPanelTitle.setFont(new Font("Arial", Font.BOLD, 16));
        seatPanelTitle.setForeground(Color.WHITE);
        centerPanel.add(seatPanelTitle, BorderLayout.NORTH);
        
        seatPanel = new JPanel();
        seatPanel.setLayout(new GridLayout(ROWS, COLS, 5, 5));
        seatPanel.setBackground(new Color(40, 40, 60));
        seatPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Initialize seats
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                seats[i][j] = new SeatButton(i, j);
                final SeatButton seat = seats[i][j];
                
                seat.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleSeatClick(seat);
                    }
                });
                seatPanel.add(seat);
            }
        }
        
        centerPanel.add(seatPanel, BorderLayout.CENTER);
        
        // Add row labels (A, B, C, etc.)
        JPanel rowLabelsPanel = new JPanel(new GridLayout(ROWS, 1, 5, 5));
        rowLabelsPanel.setBackground(new Color(30, 30, 50));
        for (int i = 0; i < ROWS; i++) {
            JLabel rowLabel = new JLabel(String.valueOf((char)('A' + i)), JLabel.CENTER);
            rowLabel.setForeground(Color.WHITE);
            rowLabel.setFont(new Font("Arial", Font.BOLD, 14));
            rowLabelsPanel.add(rowLabel);
        }
        centerPanel.add(rowLabelsPanel, BorderLayout.WEST);
        
        // Create status label
        statusLabel = new JLabel("Welcome to Cinema Booking System. Select seats to book.", JLabel.CENTER);
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        centerPanel.add(statusLabel, BorderLayout.SOUTH);

        // Create control panel
        controlPanel = new JPanel();
        controlPanel.setBackground(new Color(50, 50, 70));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JButton bookButton = new JButton("Book Selected Seats");
        bookButton.setBackground(new Color(0, 150, 0));
        bookButton.setForeground(Color.BLACK);
        bookButton.setFocusPainted(false);
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmBooking();
            }
        });
        
        JButton cancelButton = new JButton("Cancel Booking");
        cancelButton.setBackground(new Color(150, 0, 0));
        cancelButton.setForeground(Color.BLACK);
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelBooking();
            }
        });
        
        JButton showBookedButton = new JButton("Show Booked Seats");
        showBookedButton.setBackground(new Color(0, 0, 150));
        showBookedButton.setForeground(Color.BLACK);
        showBookedButton.setFocusPainted(false);
        showBookedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBookedSeats();
            }
        });
        
        JButton showAvailableButton = new JButton("Show Available Seats");
        showAvailableButton.setBackground(new Color(0, 100, 100));
        showAvailableButton.setForeground(Color.BLACK);
        showAvailableButton.setFocusPainted(false);
        showAvailableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAvailableSeats();
            }
        });
        
        JButton resetButton = new JButton("Reset All");
        resetButton.setBackground(new Color(100, 50, 0));
        resetButton.setForeground(Color.BLACK);
        resetButton.setFocusPainted(false);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetAllSeats();
            }
        });
        
        controlPanel.add(bookButton);
        controlPanel.add(cancelButton);
        controlPanel.add(showBookedButton);
        controlPanel.add(showAvailableButton);
        controlPanel.add(resetButton);

        // Add screen label
        JPanel screenPanel = new JPanel(new BorderLayout());
        screenPanel.setBackground(new Color(30, 30, 50));
        screenPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        
        JLabel screenLabel = new JLabel("SCREEN", JLabel.CENTER);
        screenLabel.setFont(new Font("Arial", Font.BOLD, 20));
        screenLabel.setForeground(Color.WHITE);
        screenLabel.setBackground(new Color(100, 100, 120));
        screenLabel.setOpaque(true);
        screenLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        screenLabel.setPreferredSize(new Dimension(800, 40));
        
        screenPanel.add(screenLabel, BorderLayout.CENTER);
        
        // Add legend panel
        JPanel legendPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        legendPanel.setBackground(new Color(30, 30, 50));
        
        JPanel availableSeat = new JPanel();
        availableSeat.setBackground(Color.GREEN);
        availableSeat.setPreferredSize(new Dimension(20, 20));
        JLabel availableLabel = new JLabel("Available");
        availableLabel.setForeground(Color.WHITE);
        
        JPanel bookedSeat = new JPanel();
        bookedSeat.setBackground(Color.RED);
        bookedSeat.setPreferredSize(new Dimension(20, 20));
        JLabel bookedLabel = new JLabel("Booked");
        bookedLabel.setForeground(Color.WHITE);
        
        JPanel selectedSeat = new JPanel();
        selectedSeat.setBackground(Color.YELLOW);
        selectedSeat.setPreferredSize(new Dimension(20, 20));
        JLabel selectedLabel = new JLabel("Selected");
        selectedLabel.setForeground(Color.WHITE);
        
        legendPanel.add(availableSeat);
        legendPanel.add(availableLabel);
        legendPanel.add(bookedSeat);
        legendPanel.add(bookedLabel);
        legendPanel.add(selectedSeat);
        legendPanel.add(selectedLabel);
        
        screenPanel.add(legendPanel, BorderLayout.SOUTH);

        // Add panels to frame
        add(screenPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        
        // Center the window on screen
        setLocationRelativeTo(null);
    }

    private void handleSeatClick(SeatButton seat) {
        if (seat.isBooked()) {
            statusLabel.setText("Seat " + seat.getSeatId() + " is already booked!");
        } else {
            if (seat.getBackground() == Color.YELLOW) {
                // Deselect the seat
                seat.setBackground(Color.GREEN);
                seat.setForeground(Color.BLACK);
                statusLabel.setText("Seat " + seat.getSeatId() + " deselected.");
            } else {
                // Select the seat for booking
                seat.setBackground(Color.YELLOW);
                seat.setForeground(Color.BLACK);
                statusLabel.setText("Seat " + seat.getSeatId() + " selected. Click 'Book Selected Seats' to confirm.");
            }
        }
    }
    
    private void confirmBooking() {
        boolean seatsSelected = false;
        StringBuilder selectedSeats = new StringBuilder("Selected seats: ");
        
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (seats[i][j].getBackground() == Color.YELLOW) {
                    seatsSelected = true;
                    selectedSeats.append(seats[i][j].getSeatId()).append(", ");
                }
            }
        }
        
        if (!seatsSelected) {
            JOptionPane.showMessageDialog(this, "No seats selected. Please select seats first.");
            return;
        }
        
        // Remove the last comma and space
        selectedSeats.delete(selectedSeats.length() - 2, selectedSeats.length());
        
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Confirm booking for:\n" + selectedSeats.toString(),
                "Confirm Booking",
                JOptionPane.YES_NO_OPTION);
                
        if (confirm == JOptionPane.YES_OPTION) {
            // Book all selected seats
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (seats[i][j].getBackground() == Color.YELLOW) {
                        seats[i][j].book();
                        bookedSeats.add(seats[i][j].getSeatId());
                    }
                }
            }
            statusLabel.setText("Seats booked successfully!");
        }
    }

    private void cancelBooking() {
        if (bookedSeats.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No seats are currently booked.");
            return;
        }

        String[] options = bookedSeats.toArray(new String[0]);
        String selectedSeat = (String) JOptionPane.showInputDialog(
                this,
                "Select a seat to cancel:",
                "Cancel Booking",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (selectedSeat != null) {
            // Find the seat and cancel booking
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (seats[i][j].getSeatId().equals(selectedSeat)) {
                        seats[i][j].cancelBooking();
                        bookedSeats.remove(selectedSeat);
                        statusLabel.setText("Booking for seat " + selectedSeat + " has been cancelled.");
                        break;
                    }
                }
            }
        }
    }

    private void showBookedSeats() {
        if (bookedSeats.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No seats are currently booked.");
        } else {
            StringBuilder message = new StringBuilder("Booked Seats:\n");
            for (String seat : bookedSeats) {
                message.append(seat).append("\n");
            }
            JOptionPane.showMessageDialog(this, message.toString());
        }
    }

    private void showAvailableSeats() {
        StringBuilder message = new StringBuilder("Available Seats:\n");
        int availableCount = 0;
        
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (!seats[i][j].isBooked()) {
                    message.append(seats[i][j].getSeatId()).append("\n");
                    availableCount++;
                }
            }
        }
        
        if (availableCount == 0) {
            JOptionPane.showMessageDialog(this, "No seats are available. The cinema is fully booked!");
        } else {
            JOptionPane.showMessageDialog(this, message.toString());
        }
    }
    
    private void resetAllSeats() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to reset all bookings?",
                "Reset Confirmation",
                JOptionPane.YES_NO_OPTION);
                
        if (confirm == JOptionPane.YES_OPTION) {
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    seats[i][j].cancelBooking();
                }
            }
            bookedSeats.clear();
            statusLabel.setText("All bookings have been reset.");
        }
    }

    public static void main(String[] args) {
        try {
            // Set look and feel to system
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CinemaBookingSystem().setVisible(true);
            }
        });
    }
} 