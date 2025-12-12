package career.java.concept.immutable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Order {

    private final String orderId;
    private final List<Item> items; // mutable list + mutable objects

    public Order(String orderId, List<Item> items) {
        this.orderId = orderId;

        // Deep copy of list + deep copy of each item
        List<Item> temp = new ArrayList<>();
        for (Item i : items) {
            temp.add(new Item(i.getName()));  // deep copy
        }
        this.items = Collections.unmodifiableList(temp); // protect list structure
    }

    public String getOrderId() {
        return orderId;
    }

    public List<Item> getItems() {
        // Return deep copy (optional due to unmodifiableList, but safer)
        List<Item> copy = new ArrayList<>();
        for (Item i : items) {
            copy.add(new Item(i.getName()));
        }
        return copy;
    }
}
