import java.util.Scanner;

public class PharmacySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pharmacy pharmacy = new Pharmacy(sc);
        int choice = 0;
        System.out.println("************************************************");
        System.out.println("Welcome To EmarPharma Pharmacy Management System");
        System.out.println("************************************************");

        do {

            System.out.println("--MAIN MENU--");
            System.out.println("1. Add Medicine");
            System.out.println("2. Display Medicines");
            System.out.println("3. Search Medicine");
            System.out.println("4. Sell Medicine");
            System.out.println("5. Exit");
			System.out.println(" ");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    pharmacy.addMedicine();
                    break;
                case 2:
                    pharmacy.displayMedicine();
                    break;
                case 3:
                    //pharmacy.searchMedicine();
                    break;
                case 4:
                    pharmacy.sellMedicine();
                    break;
                case 5:
                    System.out.println("Thank you for using EmarPharma.");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
        while (choice != 5);

        sc.close();
    }
}


