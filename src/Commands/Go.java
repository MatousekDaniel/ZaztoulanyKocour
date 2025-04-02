package Commands;

import GameMechanics.House;
import GameMechanics.Room;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * A command that lets the player move around the map.
 * Reminder: At the start dialogue let players know about (napiš 'exit' pro ukončení):
 */
public class Go extends Command {
    private House house;


    public Go(House house) {
        this.house = house;
    }

    @Override
    public String execute() {
        Scanner scanner = new Scanner(System.in);
        Room currentRoom = house.getCurrentRoom();

        System.out.println("Jsi v místnosti: " + currentRoom.getName());
        System.out.print("Dostupné místnosti: \n");
        for (Room r : currentRoom.getConnectedRooms()) {
            System.out.println("- " + r.getName());
        }
        System.out.println();

        System.out.print("Kam chceš jít?\n");
        String input = scanner.nextLine().trim();

        Room nextRoom = house.getRooms().get(input);

        if (nextRoom != null && currentRoom.isConnectedTo(nextRoom)) {
            if (nextRoom.isLocked()) {
                System.out.println("Tato místnost je uzamčena.");
            } else {
                house.setCurrentRoom(nextRoom);
                System.out.println("Přesunul jsi se do: " + nextRoom.getName());

                if (nextRoom.getName().equals("tajnaMistnost")) {
                    try (BufferedReader br = new BufferedReader(new FileReader("EndDialogue.txt"))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        System.err.println("\u001B[35mChyba při načítání souboru: \u001B[91m" + e.getMessage() + "\u001B[0m");
                    }
                    System.exit(0);
                }
            }
        }

        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}