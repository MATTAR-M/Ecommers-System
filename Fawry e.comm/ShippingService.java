import java.util.List;

public class ShippingService {
    public static void ship(List<ShippableItem> items) {
        System.out.println("Shipping the following items:");
        for (ShippableItem item : items) {
            System.out.println("- " + item.getName() + ", weight: " + item.getWeight());
        }
    }
}

