package GameMechanics;

import java.io.*;
import java.util.*;

public class House {
    private Map<String, Room> rooms;
    private Room currentRoom;

    public House(String filename) {
        rooms = new HashMap<>();
        loadRooms(filename);
        currentRoom = rooms.get("chodba1");
    }

    private void loadRooms(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 3) {
                    addConnection(parts[1].trim(), parts[2].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("\u001B[35mChyba při načítání souboru: \u001B[91m" + e.getMessage() + "\u001B[0m");
        }
    }

    private void addConnection(String room1Name, String room2Name) {
        Room room1 = rooms.computeIfAbsent(room1Name, Room::new);
        Room room2 = rooms.computeIfAbsent(room2Name, Room::new);

        room1.addConnection(room2);
        room2.addConnection(room1);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Map<String, Room> getRooms() {
        return rooms;
    }

    public void start() {
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

            Room nextRoom = rooms.get(input);
            if (nextRoom != null && currentRoom.isConnectedTo(nextRoom)) {
                currentRoom = nextRoom;
                System.out.println("Přesunul jsi se do: " + currentRoom.getName());
            } else {
                System.out.println("Nemůžeš jít tam, kam chceš.");
            }
        }

        scanner.close();
    }
}