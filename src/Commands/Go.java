package Commands;

import GameMechanics.House;
import GameMechanics.Room;

import java.util.Scanner;

public class Go extends Command{

    private House house;
    private Room currentRoom;

    @Override
    public String execute() {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Jsi v místnosti: " + currentRoom.getName());
            System.out.print("Dostupné místnosti: ");
            for (Room r : currentRoom.getConnectedRooms()) {
                System.out.print(r.getName() + " ");
            }
            System.out.println();

            System.out.print("Kam chceš jít? (napiš 'exit' pro ukončení): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) break;

            Room nextRoom = house.getRooms().get(input);

            if (nextRoom != null && currentRoom.isConnectedTo(nextRoom)) {
                currentRoom = nextRoom;
                System.out.println("Přesunul jsi se do: " + currentRoom.getName());
            } else {
                System.out.println("Nemůžeš jít tam, kam chceš.");
            }
        }

        scanner.close();
        return null;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
