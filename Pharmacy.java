import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Pharmacy {
    private final List<Medicine> inventory = new ArrayList<>();
    private final Scanner sc;

    public Pharmacy(Scanner sc) {
        this.sc = sc;
        // Pre-populating inventory data for testing
        inventory.add(new Medicine(101, "Paracetamol", 50, 15.00));
        inventory.add(new Medicine(102, "Amoxicillin", 20, 120.50));
    }

    // CRASH GUARD: Safely grabs integers from user console typing
    private int readValidInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = sc.nextInt();
                sc.nextLine(); // Clear leftover scanner newline
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry: Please enter a valid whole number.");
                sc.nextLine(); // Throw away bad input
            }
        }
    }

    // CRASH GUARD: Safely grabs decimals (prices) from user console typing
    private double readValidDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = sc.nextDouble();
                sc.nextLine(); // Clear leftover scanner newline
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry: Please enter a valid decimal price amount.");
                sc.nextLine(); // Throw away bad input
            }
        }
    }

    // Core lookup routine
    private Medicine findMedicineById(int id) {
        for (Medicine med : inventory) {
            if (med.getId() == id) return med;
        }
        return null;
    }

    // Action: Add stock, decrease stock, modify price, or add completely new items
    public void updateMedicine() {
        System.out.println("\n---- STAFF: INVENTORY UPDATE ----");
        int id = readValidInt("Enter Medicine Id: ");
        Medicine med = findMedicineById(id);

        if (med != null) {
            System.out.println("Item Found: " + med.getName());
            System.out.println("1. Add Stock | 2. Remove Stock | 3. Update Price");
            int choice = readValidInt("Enter choice: ");

            switch (choice) {
                case 1 -> {
                    int toAdd = readValidInt("Enter quantity to add: ");
                    if (toAdd < 0) {
                        System.out.println(" Error: Cannot add negative stock.");
                    } else {
                        med.setQuantity(med.getQuantity() + toAdd);
                        System.out.println("Stock updated successfully.");
                    }
                }
                case 2 -> {
                    int toRemove = readValidInt("Enter quantity to remove: ");
                    if (toRemove < 0) {
                        System.out.println(" Error: Quantity cannot be negative.");
                    } else if (med.getQuantity() >= toRemove) {
                        med.setQuantity(med.getQuantity() - toRemove);
                        System.out.println("Stock removed successfully.");
                    } else {
                        System.out.println(" Error: Insufficient stock.");
                    }
                }
                case 3 -> {
                    double newPrice = readValidDouble("Enter new unit price: ");
                    if (newPrice < 0) {
                        System.out.println(" Error: Price cannot be negative.");
                    } else {
                        med.setPrice(newPrice);
                        System.out.println("Price modified successfully.");
                    }
                }
                default -> System.out.println("Invalid operation selection.");
            }
        } else {
            System.out.println("ID not registered.");
            int addChoice = readValidInt("Register as new medicine? (1. Yes / 2. No): ");

            if (addChoice == 1) {
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                
                int quantity = readValidInt("Enter Initial Qty: ");
                double price = readValidDouble("Enter Unit Price: ");

                if (quantity < 0 || price < 0) {
                    System.out.println(" Error: Quantity and price must be positive values.");
                } else {
                    inventory.add(new Medicine(id, name, quantity, price));
                    System.out.println("New record committed successfully!");
                }
            }
        }
    }

    // Action: Handles customer transactions and keeps dynamic tally blocks
    public void sellMedicine() {
        System.out.println("\n---- CASHIER: BILLING REGISTER ----");
        if (inventory.isEmpty()) {
            System.out.println("Inventory data missing.");
            return;
        }

        double totalBill = 0;
        int sellChoice = 1;

        while (sellChoice == 1) {
            int id = readValidInt("Enter Requested Medicine ID: ");
            Medicine med = findMedicineById(id);

            if (med != null) {
                System.out.println("Item: " + med.getName() + " | Stock Available: " + med.getQuantity());
                int qtyToSell = readValidInt("Enter Quantity to checkout: ");

                if (qtyToSell <= 0) {
                    System.out.println(" Error: Quantity must be greater than 0.");
                } else if (med.getQuantity() >= qtyToSell) {
                    med.setQuantity(med.getQuantity() - qtyToSell);
                    double currentItemBill = med.getPrice() * qtyToSell;
                    totalBill += currentItemBill;
                    System.out.printf("Item billed. Subtotal: Rs.%.2f\n", currentItemBill);
                } else {
                    System.out.println(" Transaction Blocked: Not enough stock.");
                }
            } else {
                System.out.println("Product not registered.");
            }

            sellChoice = readValidInt("1. Bill another item | 2. Close final receipt: ");
        }

        System.out.println("\n======= EMARPHARMA RETAIL RECEIPT =======");
        System.out.printf("Total Cash Collected: Rs.%.2f\n", totalBill);
        System.out.println("=========================================");
    }

    // Action: Display complete dataset
    public void displayMedicine() {
        System.out.println("\n----------------------- MEDICINE CATALOG -----------------------");
        if (inventory.isEmpty()) {
            System.out.println("Catalog is completely empty.");
            return;
        }
        System.out.printf("%-10s %-25s %-13s %-10s\n", "ID", "Name", "Price", "Available Stock");
        System.out.println("-----------------------------------------------------------------");
        for (Medicine med : inventory) {
            System.out.println(med);
        }
    }

    // Action: Search filters (Numerical ID vs Keyword match cases)
    public void searchMedicine() {
        System.out.println("\n---- SEARCH MEDICINE ----");
        System.out.println("1. Find by ID\n2. Find by Name Keyword");
        int searchType = readValidInt("Selection: ");

        boolean found = false;

        if (searchType == 1) {
            int id = readValidInt("Enter Medicine ID: ");
            Medicine med = findMedicineById(id);
            if (med != null) {
                printSearchHeader();
                System.out.println(med);
                found = true;
            }
        } else if (searchType == 2) {
            System.out.print("Enter keyword: ");
            String keyword = sc.nextLine().toLowerCase();
            for (Medicine med : inventory) {
                if (med.getName().toLowerCase().contains(keyword)) {
                    if (!found) printSearchHeader();
                    System.out.println(med);
                    found = true;
                }
            }
        } else {
            System.out.println("Invalid option selected.");
            return;
        }

        if (!found) System.out.println("No matching records found.");
    }

    private void printSearchHeader() {
        System.out.printf("\n%-10s %-25s %-13s %-10s\n", "ID", "Name", "Price", "Stock");
        System.out.println("-----------------------------------------------------------------");
    }
}