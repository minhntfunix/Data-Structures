import java.util.Scanner;

public class Product {
    public String ID;
    public String title;
    public int quantity;
    public double price;


    public Product(String ID, String title, int quantity, double price) {
        this.ID = ID;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }



    @Override
    public String toString() {
        return
                ID + "," + title + "," + quantity +","+ price;

    }
}
