import java.util.*;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addProduct(Product product, int quantity) {
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Not enough stock for " + product.getName());
        }
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double calculateSubtotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public double calculateShippingFee() {
        double totalWeight = 0;
        for (CartItem item : items) {
            if (item.getProduct() instanceof ShippableItem) {
                ShippableItem si = (ShippableItem) item.getProduct();
                totalWeight += si.getWeight() * item.getQuantity();
            }
        }
        return totalWeight ;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
