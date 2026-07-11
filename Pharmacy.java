import java.util.Scanner;

public class Pharmacy {
    Scanner sc ;
    int id = 0;
    String name = " ";
    float price = 0.00f;
    String[] medicine = new String[100];
    int[] medicineId = new int[100];
    int[] medicineQuantity = new int[100];
    float[] medicinePrice = new float[100];
    int quantity = 0;
    double totalBill = 0;
    int choice = 1;
    int sellChoice =1;
    public Pharmacy(Scanner sc) {
        this.sc = sc;
    }

    int add = 0;
    int count = 0;

    public void updateMedicine() {

        System.out.println("---- MEDICINE UPDATING SECTION -----");
        System.out.print("Enter Medicine Id: ");
        id = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < count; i++) {
            if (medicineId[i] == id) {

                System.out.println("Medicine Name: " + medicine[i]);
                System.out.println("Current Quantity: " + medicineQuantity[i]);
                System.out.println("Current Price: Rs." + medicinePrice[i]);
                System.out.println("1. Add medicine stock");
                System.out.println("2. Remove medicine stock");
                System.out.println("3. Update medicine price");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                switch (choice) {
				case 1:
					System.out.print("Enter quantity to add: ");
					quantity = sc.nextInt();
					medicineQuantity[i] = medicineQuantity[i] + quantity;
					System.out.println("Stock added successfully.");
					System.out.println("New Quantity: " + medicineQuantity[i]);
					break;
                case 2:
					System.out.print("Enter quantity to remove: ");
					quantity = sc.nextInt();
					if (medicineQuantity[i] >= quantity) {
						medicineQuantity[i] = medicineQuantity[i] - quantity;
						System.out.println("Stock removed successfully.");
						System.out.println("Remaining Quantity: " + medicineQuantity[i]);
					} else {
						System.out.println("Insufficient stock.");
					}
					break;
				case 3:
					System.out.print("Enter new price: ");
					price = sc.nextFloat();
					medicinePrice[i] = price;
					System.out.println("Price updated successfully.");
					System.out.println("New Price: Rs." + medicinePrice[i]);
					break;
				default:
					System.out.println("Invalid choice.");
                }
                return;
            }
        }
        System.out.println("Medicine not found.");
        System.out.println("Do you want to add this new medicine?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Enter choice: ");
        add = sc.nextInt();
        if (add == 1) {
            System.out.print("Enter Medicine Name: ");
            sc.nextLine();
            name = sc.nextLine();
            System.out.print("Enter quantity: ");
            quantity = sc.nextInt();
            System.out.print("Enter unit price: ");
            price = sc.nextFloat();
            medicine[count] = name;
            medicineId[count] = id;
            medicineQuantity[count] = quantity;
            medicinePrice[count] = price;
            count++;
            System.out.println("New medicine added successfully.");
        }
        else {
            System.out.println("Medicine was not added.");
        }
    }

    public void sellMedicine() {
        System.out.println("----MEDICINE SALES SECTION----");

        while (sellChoice == 1) {
            int i = 0;
            System.out.print("Enter Medicine Id: ");
            id = sc.nextInt();
            for (i = 0; i < count; i++) {
                if (medicineId[i] == id) {
                    System.out.println("Medicine Name: " + medicine[i]);
                    System.out.println("Price: Rs." + medicinePrice[i]);
                    System.out.println("Available Stock: " + medicineQuantity[i]);
                    System.out.print("Enter quantity to sell: ");
                    quantity = sc.nextInt();

                    if (medicineQuantity[i] >= quantity) {

                        medicineQuantity[i] = medicineQuantity[i] - quantity;

                        double bill = medicinePrice[i] * quantity;
                        totalBill = totalBill + bill;

                        System.out.println("Medicine added to bill");
                        System.out.println("Amount: Rs." + bill);

                    } 
					else {
                        System.out.println("Insufficient Stock");
                    }

                    break;
                }
            }
            if (i == count){
                System.out.println("Medicine not available");
            }
            System.out.println("1. Add another medicine");
            System.out.println("2. Finish sale");
            System.out.print("Enter choice: ");
            sellChoice = sc.nextInt();
        }

        System.out.println("------- FINAL BILL -------");
        System.out.println("Total Amount: Rs." + totalBill);
        System.out.println("--------------------------");

        System.out.println("Sale Successful");
    }


    public void displayMedicine() {

        System.out.println("-----MEDICINE LIST-----");
        System.out.println("ID\tName\t\t\tPrice\tStock");

        for (int i = 0; i < count; i++) {
            System.out.println(medicineId[i] + "\t" + medicine[i] + "\t\t Rs." + medicinePrice[i] + "\t" + medicineQuantity[i]);
        }
    }

    public void searchMedicine() {
        System.out.println("----SEARCH MEDICINE SECTION----");
        System.out.print("To search Enter medicine ID:");
        id = sc.nextInt();
        for (int i = 0; i < count; i++) {
            if (id == medicineId[i]) {
                System.out.println("ID\tName\t\tPrice\tStock");
                System.out.println(medicineId[i] + "\t" + medicine[i] + "\t\t Rs." + medicinePrice[i] + "\t" + medicineQuantity[i]);
                return;
            }
        }
        System.out.println("Medicine ID not found");
        }
}