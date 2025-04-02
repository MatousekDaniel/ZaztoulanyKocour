package Commands;

import GameMechanics.House;
import GameMechanics.Item;
import GameMechanics.Objekkt;
import GameMechanics.Quest;
import GameMechanics.Room;

import java.util.List;
import java.util.Scanner;

/**
 * Takes an item out of a furniture and adds it to the players inventory.
 */
public class Take extends Command {
    private House house;

    public Take(House house) {
        this.house = house;
    }

    @Override
    public String execute() {
        Quest activeQuest = house.getActiveQuest();

        if (activeQuest == null || !activeQuest.isActive()) {
            return "Momentálně nemáš aktivní žádný úkol, takže nic nepotřebuješ.";
        }

        Room currentRoom = house.getCurrentRoom();
        List<Objekkt> objects = currentRoom.getObjects();


        if (objects.isEmpty()) {
            return "V této místnosti nejsou žádné předměty, které bys mohl vzít.";
        }

        System.out.println("Předměty v této místnosti:");
        boolean hasItems = false;

        for (Objekkt obj : objects) {
            for (Item item : obj.getItems()) {
                System.out.println("- " + item.getName());
                hasItems = true;
            }
        }

        if (!hasItems) {
            return "V této místnosti nejsou žádné předměty, které bys mohl vzít.";
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Zadej název předmětu, který chceš vzít: ");
        String itemName = scanner.nextLine().trim();

        if (itemName.isEmpty()) {
            return "Musíš zadat název předmětu!";
        }


        for (Objekkt obj : objects) {
            List<Item> items = obj.getItems();
            for (Item item : items) {
                if (item.getName().equalsIgnoreCase(itemName)) {
                    obj.getItems().remove(item);
                    house.getUserInventory().addItem(item);
                    return "Předmět " + itemName + " byl přidán do tvého inventáře.";
                }
            }
        }

        return "Předmět " + itemName + " v této místnosti není.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}