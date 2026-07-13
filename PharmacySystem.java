import java.util.Scanner;
import java.util.InputMismatchException;

public class PharmacySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pharmacy pharmacy = new Pharmacy(sc);
        int roleChoice;

        System.out.println("************************************************");
        System.out.println("Welcome To EmarPharma Pharmacy Management System");
        System.out.println("************************************************");

        // Loop runs until option 4 is specifically selected
        do {
            System.out.println("\n=== Identify Your Identity Role ===");
            System.out.println("1. Customer");
            System.out.println("2. Pharmacy Staff (Inventory Control)");
            System.out.println("3. Cashier (Sales Register)");
            System.out.println("4. Shut Down System");
            
            roleChoice = safeReadInt(sc, "Select User Portal: ");

            switch (roleChoice) {
                case 1 -> runCustomerMenu(sc, pharmacy);
                case 2 -> runStaffMenu(sc, pharmacy);
                case 3 -> runCashierMenu(sc, pharmacy);
                case 4 -> System.out.println("\nSystem shutting down down safely. Goodbye!");
                default -> System.out.println("Access Denied: Invalid Portal Role.");
            }
        } while (roleChoice != 4);

        sc.close();
    }

    // UI Crash Guard: Protects system choice selections from bad typography inputs
    private static int safeReadInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = sc.nextInt();
                sc.nextLine(); // Clear scanner residual newline
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid structural entry: Numbers only please.");
                sc.nextLine(); // Throw away bad input
            }
        }
    }

    // SUB-PORTAL: Customer Functions Router View
    private static void runCustomerMenu(Scanner sc, Pharmacy pharmacy) {
        int choice;
        do {
            System.out.println("\n-- CUSTOMER PORTAL --");
            System.out.println("1. View Available Medicine Catalog");
            System.out.println("2. Search Medicine by Name/ID");
            System.out.println("3. Back to Main Entrance");
            
            choice = safeReadInt(sc, "Choice: ");

            switch (choice) {
                case 1 -> pharmacy.displayMedicine();
                case 2 -> pharmacy.searchMedicine();
                case 3 -> System.out.println("Exiting Customer View...");
                default -> System.out.println("Invalid Input.");
            }
        } while (choice != 3);
    }

    // SUB-PORTAL: Staff Functions Router View
    private static void runStaffMenu(Scanner sc, Pharmacy pharmacy) {
        int choice;
        do {
            System.out.println("\n-- INVENTORY STAFF PORTAL --");
            System.out.println("1. Update Stock / Add New Medicine");
            System.out.println("2. View Current Inventory Records");
            System.out.println("3. Search Medicine Records");
            System.out.println("4. Back to Main Entrance");
            
            choice = safeReadInt(sc, "Choice: ");

            switch (choice) {
                case 1 -> pharmacy.updateMedicine();
                case 2 -> pharmacy.displayMedicine();
                case 3 -> pharmacy.searchMedicine();
                case 4 -> System.out.println("Logging out from Staff Portal...");
                default -> System.out.println("Invalid Input.");
            }
        } while (choice != 4);
    }

    // SUB-PORTAL: Cashier Functions Router View
    private static void runCashierMenu(Scanner sc, Pharmacy pharmacy) {
        int choice;
        do {
            System.out.println("\n-- CASHIER SALES PORTAL --");
            System.out.println("1. Process Customer Sale & Bill");
            System.out.println("2. Check Stock Levels");
            System.out.println("3. Search Medicine Item");
            System.out.println("4. Back to Main Entrance");
            
            choice = safeReadInt(sc, "Choice: ");

            switch (choice) {
                case 1 -> pharmacy.sellMedicine();
                case 2 -> pharmacy.displayMedicine();
                case 3 -> pharmacy.searchMedicine();
                case 4 -> System.out.println("Logging out from Cashier Portal...");
                default -> System.out.println("Invalid Input.");
            }
        } while (choice != 4);
    }
}