package Commands;

import GameMechanics.House;
import GameMechanics.Objekkt;
import GameMechanics.Room;

import java.util.List;

public class Explore extends Command {
    private House house;

    public Explore(House house) {
        this.house = house;
    }

    @Override
    public String execute() {
        Room currentRoom = house.getCurrentRoom();
        StringBuilder output = new StringBuilder("Nacházíš se v místnosti: " + currentRoom.getName() + "\n");

        List<Objekkt> objects = currentRoom.getObjects();
        if (objects.isEmpty()) {
            output.append("V této místnosti není žádný nábytek.");
        } else {
            output.append("V místnosti se nachází\n");
            for (Objekkt obj : objects) {
                output.append("- ").append(obj.getName()).append("\n");
            }
        }

        return output.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}