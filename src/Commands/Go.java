package Commands;

import GameMechanics.House;
import GameMechanics.Room;

import java.util.Scanner;

public class Go extends Command{

    private House house = new House("RoomConnection.txt");
    private Room currentRoom;

    @Override
    public String execute() {

        currentRoom = house.getRooms().get("chodba1");

        Scanner scanner = new Scanner(System.in);

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
                currentRoom = nextRoom;
                System.out.println("Přesunul jsi se do: " + currentRoom.getName());
            }else {
                System.out.println("Nemůžeš jít tam, kam chceš.");
            }

        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
