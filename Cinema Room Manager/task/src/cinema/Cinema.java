package cinema;

import java.util.Scanner;

public class Cinema {
    private final View view;
    private final Controller controller;

    public Cinema() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        System.out.println();

        view = new View(rows, seats);
        controller = new Controller(view);
    }

    public void run() {
        while (!controller.isFinished()) {
            controller.showMenu();
        }
    }
}