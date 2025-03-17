package GameMechanics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Objekkt {
    private String name;
    private Room room;
    private List<Item> items;

    public Objekkt(String name, Room room, String itemsFile) {
        this.name = name;
        this.room = room;
        this.items = new ArrayList<>();
        loadItems(itemsFile); // Každý nábytek si načte své předměty
    }

    public String getName() {
        return name;
    }

    public Room getRoom() {
        return room;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    private void loadItems(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 2) {
                    String furnitureName = parts[0].trim();
                    String itemName = parts[1].trim();

                    if (furnitureName.equalsIgnoreCase(this.name)) {
                        this.items.add(new Item(itemName, this));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Chyba při načítání předmětů pro " + name + ": " + e.getMessage());
        }
    }
}