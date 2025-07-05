import java.util.List;
import java.util.ArrayList;
public class Customer {
    private double balance;
    private Cart cart;
    public Customer(double balance) {
        this.balance = balance;
        this.cart = new Cart();
    }    
    public double getBalance() {
        return balance;
    }
    public Cart getCart() {
        return cart;
    }

    public void checkout() {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        } 
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().isExpired()) {
                throw new IllegalStateException(item.getProduct().getName() + " is expired");
            }
        } 
        double subtotal = cart.calculateSubtotal();
        double shippingFee = cart.calculateShippingFee();
        double total = subtotal + shippingFee;
    
        if (balance < total) {
            throw new IllegalStateException("Insufficient balance");
        }
        System.out.println("** Shipment notice **");
        double totalWeight = 0.0;
        for (CartItem item : cart.getItems()) {
            if (item.getProduct() instanceof ShippableItem) {
                ShippableItem si = (ShippableItem) item.getProduct();
                double itemWeight = si.getWeight() * item.getQuantity();
                totalWeight += itemWeight;
                System.out.printf("%dx %-15s %.0fg%n", item.getQuantity(), si.getName(), itemWeight);
            }
        }
        if (totalWeight > 0) {
            System.out.printf("Total package weight %.1fkg%n%n", totalWeight);
        }
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            double itemTotal = item.getSubtotal();
            System.out.printf("%dx %-15s %.0f%n", item.getQuantity(), item.getProduct().getName(), itemTotal);
        }
        System.out.println("------------------------");
        System.out.printf("%-20s %.0f%n", "Subtotal", subtotal);
        System.out.printf("%-20s %.0f%n", "Shipping", shippingFee);
        System.out.printf("%-20s %.0f%n", "Amount", total);
        balance -= total;
        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }
    
        List<ShippableItem> shippables = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            if (item.getProduct() instanceof ShippableItem) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippables.add((ShippableItem) item.getProduct());
                }
            }
        }
    
        if (!shippables.isEmpty()) {
            ShippingService.ship(shippables);
        }
    }
    
    
}
