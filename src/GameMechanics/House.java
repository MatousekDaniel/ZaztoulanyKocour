package GameMechanics;

import java.io.*;
import java.util.*;

public class House {
    private Map<String, Room> rooms;
    private Room currentRoom;

    public String filename;

    public House(String filename) {
        rooms = new HashMap<>();
        loadRooms(filename);
        currentRoom = rooms.get("chodba1");
    }

    public void loadRooms(String filename) {
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
}