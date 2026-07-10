import java.util.Scanner;

public class Pharmacy {
    Scanner sc = new Scanner(System.in);
    int id = 0;
    String name = " ";
    String mfname = " ";
    double price = 0.00;
    String[] medicine= new String[100];
    int[] medicineId =new int[100];
    int[] medicineQuantity = new int[100];
    int quantity = 0;
    int count = 0;
    boolean found = false;
    int add=0;

    public void addMedicine() {
        System.out.println("----ADD MEDICINE SECTION----- ");
        System.out.print("Enter Medicine Name:");
        name = sc.nextLine();
        System.out.print("Enter Medicine Id:");
        id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Manufacturer:");
        mfname = sc.nextLine();
        
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
        if(found == false) {
            System.out.println("Medicine not found.");
            System.out.println("Do you want to add this New medicine to the system");
            System.out.print("Enter 1 for yes and 2 for No:");
            add=sc.nextInt();
            if (add == 1) {
                System.out.print("Enter quantity:");
                quantity = sc.nextInt();
                System.out.print("Enter Price:");
                price = sc.nextDouble();
                medicine[count] = name;
                medicineId[count] = id;
                medicineQuantity[count] = quantity;
                System.out.println("New Quantity:" + medicineQuantity[count]);
                count++;

                System.out.println("New medicine added successfully.");
            }
        }
    }
}