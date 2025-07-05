import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Create products
        Product tv = new ShippableProduct("TV", 3000, 2, 15);
        Product cheese = new ShippableExpirableProduct("Cheese", 50, 10, LocalDate.now().plusDays(5), 1);
        Product mobileCard = new Product("Mobile Card", 100, 5);

        // Create customer
        Customer customer = new Customer( 10000);

        // Add to cart
        customer.getCart().addProduct(tv, 1);
        customer.getCart().addProduct(cheese, 2);
        customer.getCart().addProduct(mobileCard, 3);

        // Checkout
        customer.checkout();
    }
}
