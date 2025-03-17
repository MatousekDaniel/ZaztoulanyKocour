package GameMechanics;

import java.io.*;
import java.util.*;

public class House {
    private Map<String, Room> rooms;
    private Room currentRoom;

    public House(String roomFile, String furnitureFile, String itemsFile) {
        rooms = new HashMap<>();
        loadRooms(roomFile, furnitureFile, itemsFile);
    }

    private void loadRooms(String roomFile, String furnitureFile, String itemsFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(roomFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");

                if (parts.length == 3) {
                    String room1 = parts[1].trim();
                    String room2 = parts[2].trim();

                    // Pokud místnosti ještě nejsou v mapě, vytvoříme je
                    rooms.putIfAbsent(room1, new Room(room1, furnitureFile, itemsFile));
                    rooms.putIfAbsent(room2, new Room(room2, furnitureFile, itemsFile));

                    // Přidání propojení
                    addConnection(room1, room2);
                }
            }

            // Nastavení výchozí místnosti
            currentRoom = rooms.getOrDefault("chodba1", rooms.values().stream().findFirst().orElse(null));

        } catch (IOException e) {
            System.err.println("Chyba při načítání místností: " + e.getMessage());
        }
    }

    private void addConnection(String room1Name, String room2Name) {
        // Získání místností z mapy
        Room room1 = rooms.get(room1Name);
        Room room2 = rooms.get(room2Name);

        // Připojení místností
        if (room1 != null && room2 != null) {
            room1.addConnection(room2);
            room2.addConnection(room1);
        } else {
            System.err.println("Propojení místností " + room1Name + " a " + room2Name + " neprobíhá, místnosti neexistují.");
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public Map<String, Room> getRooms() {
        return rooms;
    }
}