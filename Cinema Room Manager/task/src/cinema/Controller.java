package cinema;

import java.util.Scanner;

public class Controller {
    private static int currentIncome = 0;
    private static int ticketsSold = 0;
    private final View view;
    private boolean isFinished = false;

    public Controller(View view) {
        this.view = view;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice < 0 || choice > 3) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    isFinished = true;
                    break;
                case 1:
                    view.drawCinemaHall();
                    break;
                case 2:
                    seatBooking();
                    break;
                case 3:
                    showStats();
                    break;
            }
        }
    }

    private void showStats() {
        System.out.printf("Number of purchased tickets: %d%n", ticketsSold);
        System.out.printf("Percentage: %.2f%%%n", getPercentageOfPurchasedTickets());
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", getTotalIncome());
        System.out.println();
    }


    private void seatBooking() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int bookedRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int bookedSeat = scanner.nextInt();

        if (checkIfSeatIsFree(bookedRow, bookedSeat)) {
            Seat seat = view.getSeats()[bookedRow - 1][bookedSeat - 1];
            book(seat);
            int price = calculateTicketPrice(seat);
            currentIncome += price;
            ticketsSold++;
            System.out.println();
            System.out.println("Ticket price: $" + price + "\n");
        } else {
            if (bookedRow > view.getRowsAmount() || bookedSeat > view.getSeatsAmount() ||
                    bookedSeat < 0 || bookedRow < 0) {
                System.out.println("Wrong input!");
            } else {
                System.out.println("That ticket has already been purchased!");
            }
            seatBooking();
        }
    }

    private boolean checkIfSeatIsFree(int row, int seat) {
        if (row > view.getRowsAmount() || seat > view.getSeatsAmount() ||
                seat < 0 || row < 0) {
            return false;
        }
        Seat chosenSeat = view.getSeats()[seat - 1][row - 1];
        return !chosenSeat.isBooked();
    }

    private void book(Seat seat) {
        Seat[][] seats = view.getSeats();
        seats[seat.getSeat() - 1][seat.getRow() - 1].setBooked(true);
        view.setSeats(seats);
    }

    private int calculateTicketPrice(Seat seat) {
        boolean isCostlyTicket = view.getRowsAmount() * view.getSeatsAmount() <= 60 ||
                seat.getRow() <= view.getRowsAmount() / 2;

        return isCostlyTicket ? 10 : 8;
    }

    private float getPercentageOfPurchasedTickets() {
        return (ticketsSold / (float) (view.getRowsAmount() * view.getSeatsAmount())) * 100;
    }

    private int getTotalIncome() {
        int rows = view.getRowsAmount();
        int seats = view.getSeatsAmount();

        if (rows * seats <= 60) {
            return rows * seats * 10;
        } else {
            int firstHalf = (rows / 2) * seats * 10;
            int secondHalf;
            if (rows % 2 == 0) {
                secondHalf = (rows / 2) * 8;
            } else {
                secondHalf = (rows + 1) / 2 * seats * 8;
            }
            return firstHalf + secondHalf;
        }
    }

    public boolean isFinished() {
        return isFinished;
    }
}
