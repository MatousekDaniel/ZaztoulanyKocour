package Commands;

import GameMechanics.House;
import GameMechanics.Room;

import java.util.Scanner;

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
        System.out.print("Dostupné místnosti: ");
        for (Room r : currentRoom.getConnectedRooms()) {
            System.out.print(r.getName() + " ");
        }
        System.out.println();

        System.out.print("Kam chceš jít? (napiš 'exit' pro ukončení): ");
        String input = scanner.nextLine().trim();

        Room nextRoom = house.getRooms().get(input);

        if (nextRoom != null && currentRoom.isConnectedTo(nextRoom)) {
            house.setCurrentRoom(nextRoom);
            System.out.println("Přesunul jsi se do: " + nextRoom.getName());
        } else {
            System.out.println("Nemůžeš jít tam, kam chceš.");
        }

        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}