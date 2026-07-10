import java.util.Scanner;

public class Pharmacy {
    Scanner sc ;
    int id = 0;
    String name = " ";
    String manufacturer = " ";
    float price = 0.00f;
    String[] medicine = new String[100];
    int[] medicineId = new int[100];
    int[] medicineQuantity = new int[100];
    float[] medicinePrice = new float[100];
    int quantity = 0;
    public Pharmacy(Scanner sc) {
        this.sc = sc;
    }
    int add = 0;
    int count = 0;
    
    public void addMedicine() {
        boolean found = false;
        System.out.println("----ADD MEDICINE SECTION----- ");
        System.out.print("Enter Medicine Name:");
        name = sc.nextLine();
        System.out.print("Enter Medicine Id:");
        id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Manufacturer:");
        manufacturer = sc.nextLine();

        for (int i = 0; i < count; i++) {

            if (medicineId[i] == id) {
                System.out.println("Medicine name:" + medicine[i]);
                System.out.print("Enter quantity:");
                quantity = sc.nextInt();
                medicineQuantity[i] = medicineQuantity[i] + quantity;
                System.out.println("Quantity updated successfully.");
                System.out.println("New Quantity:" + medicineQuantity[i]);
                found = true;
                break;
            }
        }
        if (found == false) {
            System.out.println("Medicine not found.");
            System.out.println("Do you want to add this New medicine to the system");
            System.out.print("Enter 1 for yes and 2 for No:");
            add = sc.nextInt();
            if (add == 1) {
                System.out.print("Enter quantity:");
                quantity = sc.nextInt();
                System.out.print("Enter Unit Price:");
                price = sc.nextFloat();
                medicine[count] = name;
                medicineId[count] = id;
                medicineQuantity[count] = quantity;
                medicinePrice[count] = price;
                System.out.println("New Quantity:" + medicineQuantity[count]);
                count++;

                System.out.println("New medicine added successfully.");
            }
        }
    }

    public void sellMedicine() {
        boolean found = false;
        System.out.println("---Medicine Sales Section---");
        System.out.print("Enter Medicine Name:");
        name = sc.nextLine();
        System.out.print("Enter Medicine Id:");
        id = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (medicineId[i] == id) {
                System.out.println("Medicine is available");
                System.out.println("Available quantity: " + medicineQuantity[i]);
                System.out.print("Enter the amount to sell:");
                quantity = sc.nextInt();
                found = true;

                if (medicineQuantity[i] >= quantity) {
                    System.out.println("Sale Successful");
                    medicineQuantity[i] = medicineQuantity[i] - quantity;
                    System.out.println("Remaining Quantity:" + medicineQuantity[i]);
                } else {
                    System.out.println("Insufficient Stocks");
                }
                break;
            }
        }
        if (found == false) {
            System.out.println("Medicine is not available");
        }
    }

    public void displayMedicine() {

        System.out.println("----- Medicine List -----");
        System.out.println("ID\tName\t\t\tPrice\tStock");

        for (int i = 0; i < count; i++) {
            System.out.println(medicineId[i] + "\t" + medicine[i] + "\t\t Rs." + medicinePrice[i] + "\t" + medicineQuantity[i]);
        }
    }

    public void searchMedicine() {
        boolean found = false;
        System.out.println("---- Search Medicine Section ----");
        System.out.print("To search Enter medicine ID:");
        id = sc.nextInt();
        for (int i = 0; i < count; i++) {
            if (id == medicineId[i]) {
                System.out.println("ID\tName\t\tPrice\tStock");
                System.out.println(medicineId[i] + "\t" + medicine[i] + "\t\tRs." + medicinePrice[i] + "\t" + medicineQuantity[i]);
                found = true;
                break;
            }
        }
        if (found == false) {
            System.out.println("Medicine ID not found");
        }
    }
}