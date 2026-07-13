public class Medicine {
    private int id;
    private String name;
    private int quantity;
    private double price;

    // Constructor to initialize medicine details
    public Medicine(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    // Tabular formatting layout for displaying the item
    @Override
    public String toString() {
        return String.format("%-10d %-25s Rs.%-10.2f %-10d", id, name, price, quantity);
    }
}