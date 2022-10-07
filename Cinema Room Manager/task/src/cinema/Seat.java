package cinema;

public class Seat {
    private final int row;
    private final int seat;
    private boolean isBooked;

    public Seat(int row, int seat, boolean isBooked) {
        this.row = row;
        this.seat = seat;
        this.isBooked = isBooked;
    }

    @Override
    public String toString() {
        return isBooked ? "B" : "S";
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }
}
