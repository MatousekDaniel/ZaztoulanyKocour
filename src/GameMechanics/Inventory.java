package GameMechanics;

import java.util.ArrayList;
import java.util.List;

/**
 * The inventory system.
 */
public class Inventory {
    private List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }
}