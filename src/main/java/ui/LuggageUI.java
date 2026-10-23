package ui;

import dao.ScenarioDao;
import model.Luggage;

import java.util.List;
import java.util.Scanner;

public class LuggageUI {
    private static final String LINE = "=".repeat(56);
    private static final String WIDE_LINE = "=".repeat(90);
    private static final String BORDER = "+----+--------------------+-----------+-------------+----------------+";

    private final Scanner sc = new Scanner(System.in);
    private final ScenarioDao dao = new ScenarioDao();

    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Enter choice: ");
            String input = sc.nextLine().trim();   // nextLine() avoids leftover newline issues

            switch (input) {
                case "1":
                    checkInLuggage();
                    break;
                case "2":
                    viewLuggage();
                    break;
                case "3":
                    System.out.println("\nExiting system... Thank you!");
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid choice. Please enter 1, 2, or 3.");
            }
        }
        sc.close();
    }

    private void printMenu() {
        System.out.println("\n" + LINE);
        System.out.println("          AIRPORT LUGGAGE CLAIM REGISTRY");
        System.out.println(LINE);
        System.out.println("[1] Check-In Luggage");
        System.out.println("[2] View Checked Luggage");
        System.out.println("[3] Exit System");
        System.out.println(LINE);
    }

    private void checkInLuggage() {
        System.out.println("\n--- CHECK-IN LUGGAGE ---");

        String name;
        do {
            System.out.print("Enter Passenger Name: ");
            name = sc.nextLine().trim();
            if (name.isEmpty()) System.out.println("Name cannot be empty.");
        } while (name.isEmpty());

        String flight;
        do {
            System.out.print("Enter Flight Number: ");
            flight = sc.nextLine().trim();
            if (flight.isEmpty()) System.out.println("Flight number cannot be empty.");
        } while (flight.isEmpty());

        double weight;
        while (true) {
            System.out.print("Enter Luggage Weight (KG): ");
            try {
                weight = Double.parseDouble(sc.nextLine().trim());
                if (weight <= 0) {
                    System.out.println("Weight must be greater than 0.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }

        String status;
        while (true) {
            System.out.print("Enter Status (CHECKED / LOADED / CLAIMED): ");
            status = sc.nextLine().trim().toUpperCase();
            if (status.equals("CHECKED") || status.equals("LOADED") || status.equals("CLAIMED")) {
                break;
            }
            System.out.println("Invalid status. Choose CHECKED, LOADED, or CLAIMED.");
        }

        if (dao.addRecord(new Luggage(name, flight, weight, status))) {
            System.out.println("\nSUCCESS: Baggage successfully checked in!");
        } else {
            System.out.println("\nERROR: Failed to check in baggage.");
        }
    }

    private void viewLuggage() {
        List<Luggage> records = dao.getAllRecords();

        System.out.println("\n" + WIDE_LINE);
        System.out.println("                              CHECKED LUGGAGE LOGS");
        System.out.println(WIDE_LINE);
        System.out.println(BORDER);
        System.out.printf("| %-2s | %-18s | %-9s | %-11s | %-14s |%n",
                "ID", "PASSENGER NAME", "FLIGHT NO", "WEIGHT_KG", "STATUS");
        System.out.println(BORDER);

        for (Luggage l : records) {
            System.out.printf("| %-2d | %-18s | %-9s | %-11.1f | %-14s |%n",
                    l.getId(), l.getPassengerName(), l.getFlightNo(),
                    l.getWeightKg(), l.getStatus());
        }

        System.out.println(BORDER);
        System.out.println("Total Records Displayed: " + records.size());
    }
}
