# Cinema Seat Booking System

A Java application that simulates a cinema seat booking system with a graphical user interface.

## Features

- Visual representation of cinema seats in a grid layout
- Color-coded seats: Green (available), Yellow (selected), Red (booked)
- Ability to select and book multiple seats at once
- Cancel bookings for previously booked seats
- View lists of booked and available seats
- Reset all bookings
- User-friendly interface with status updates

## How to Run

1. Make sure you have Java installed on your system (Java 8 or higher recommended)
2. Compile the Java files:
   ```
   javac CinemaBookingSystem.java SeatButton.java
   ```
3. Run the application:
   ```
   java CinemaBookingSystem
   ```

## Usage Instructions

1. **Selecting Seats**:
   - Click on any green (available) seat to select it
   - Selected seats will turn yellow
   - Click a yellow seat to deselect it

2. **Booking Seats**:
   - Select one or more seats
   - Click the "Book Selected Seats" button
   - Confirm your booking in the dialog
   - Booked seats will turn red

3. **Cancelling Bookings**:
   - Click the "Cancel Booking" button
   - Select the seat you want to cancel from the dropdown menu
   - The seat will become available again (green)

4. **Viewing Seat Information**:
   - Click "Show Booked Seats" to see a list of all booked seats
   - Click "Show Available Seats" to see a list of all available seats

5. **Resetting All Bookings**:
   - Click the "Reset All" button to clear all bookings
   - Confirm the reset in the dialog
   - All seats will return to available status (green)

## System Requirements

- Java Runtime Environment (JRE) 8 or higher
- Minimum screen resolution: 800x600

## Implementation Details

The application is built using Java Swing for the graphical user interface. It consists of two main classes:

1. **CinemaBookingSystem.java**: The main application class that creates the UI and handles user interactions
2. **SeatButton.java**: A custom button class that represents individual cinema seats

The cinema layout is configured for 8 rows and 10 columns (80 seats total), but this can be easily modified by changing the ROWS and COLS constants in the CinemaBookingSystem class. 
