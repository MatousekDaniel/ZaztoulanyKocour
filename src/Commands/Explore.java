package Commands;

import GameMechanics.House;
import GameMechanics.Objekkt;
import GameMechanics.Room;
import GameMechanics.Chara;

import java.util.List;

/**
 * Writes out the furniture and characters that the player can find in the current room.
 */
public class Explore extends Command {
    private House house;

    public Explore(House house) {
        this.house = house;
    }

    @Override
    public String execute() {
        Room currentRoom = house.getCurrentRoom();
        StringBuilder output = new StringBuilder("Nacházíš se v místnosti: " + currentRoom.getName() + "\n"); //StringBuilder creates an object and then rewrites its data unlike String that makes a new object every time

        List<Objekkt> objects = currentRoom.getObjects();
        if (objects.isEmpty()) {
            output.append("V této místnosti není žádný nábytek.\n"); // Append make any other data type a String
        } else {
            output.append("V místnosti se nachází:\n");
            for (Objekkt obj : objects) {
                output.append("- ").append(obj.getName()).append("\n");
            }
        }

        List<Chara> characters = currentRoom.getCharacters();
        if (characters.isEmpty()) {
            output.append("V místnosti není žádná postava.");
        } else {
            output.append("V místnosti vidíš:\n");
            for (Chara character : characters) {
                output.append("- ").append(character.getName());
            }
        }

        return output.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}