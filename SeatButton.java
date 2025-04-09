import javax.swing.*;
import java.awt.*;

public class SeatButton extends JButton {
    private int row;
    private int column;
    private boolean isBooked;
    private String seatId;
    
    public SeatButton(int row, int column) {
        this.row = row;
        this.column = column;
        this.isBooked = false;
        this.seatId = "R" + (row + 1) + "C" + (column + 1);
        
        setText(seatId);
        setBackground(Color.GREEN);
        setForeground(Color.BLACK);
        setFocusPainted(false);
        setBorderPainted(true);
        setFont(new Font("Arial", Font.BOLD, 10));
        setPreferredSize(new Dimension(60, 40));
    }
    
    public void book() {
        isBooked = true;
        setBackground(Color.RED);
        setForeground(Color.WHITE);
    }
    
    public void cancelBooking() {
        isBooked = false;
        setBackground(Color.GREEN);
        setForeground(Color.BLACK);
    }
    
    public boolean isBooked() {
        return isBooked;
    }
    
    public String getSeatId() {
        return seatId;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }
} 