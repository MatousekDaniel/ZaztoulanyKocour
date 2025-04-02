package Commands;

import GameMechanics.House;
import GameMechanics.Item;
import GameMechanics.Objekkt;
import GameMechanics.Room;

import java.util.List;
import java.util.Scanner;

/**
 * Lets the player look into the furniture to see if it includes any items.
 */
public class Look extends Command {
    private House house;

    public Look(House house) {
        this.house = house;
    }

    @Override
    public String execute() {
        Room currentRoom = house.getCurrentRoom();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Co si chceš prohlédnout? (napiš název nábytku)");
        String input = scanner.nextLine().trim();

        for (Objekkt objekkt : currentRoom.getObjects()) {
            if (objekkt.getName().equalsIgnoreCase(input)) {
                List<Item> items = objekkt.getItems();
                if (items.isEmpty()) {
                    return "V " + objekkt.getName() + " nic není.";
                } else {
                    StringBuilder output = new StringBuilder("V " + objekkt.getName() + " se nachází:\n");
                    for (Item item : items) {
                        output.append("- ").append(item.getName()).append("\n");
                    }
                    return output.toString();
                }
            }
        }
        return "Takový nábytek zde není.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}