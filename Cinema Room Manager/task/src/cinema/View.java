package cinema;

public class View {
    private final int rowsAmount;
    private final int seatsAmount;
    private Seat[][] seats;

    public View(int rowsAmount, int seatsAmount) {
        this.rowsAmount = rowsAmount;
        this.seatsAmount = seatsAmount;
        this.seats = new Seat[seatsAmount][rowsAmount];
        initCinemaHall();
    }

    private void initCinemaHall() {
        for (int i = 0; i < rowsAmount; i++) {
            for (int k = 0; k < seatsAmount; k++) {
                seats[k][i] = new Seat(k + 1, i + 1, false);
            }
        }
    }

    public void drawCinemaHall() {
        System.out.println("Cinema:");
        for (int i = 0; i <= rowsAmount; i++) {
            for (int k = 0; k <= seatsAmount; k++) {
                if (i == 0) {
                    if (k != 0) {
                        System.out.print(k + " ");
                    } else {
                        System.out.print("  ");
                    }
                } else {
                    if (k == 0) {
                        System.out.print(i + " ");
                    } else {
                        System.out.print(seats[k - 1][i - 1] + " ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getRowsAmount() {
        return rowsAmount;
    }

    public int getSeatsAmount() {
        return seatsAmount;
    }

    public Seat[][] getSeats() {
        return seats;
    }

    public void setSeats(Seat[][] seats) {
        this.seats = seats;
    }
}
