package GameMechanics;

import java.io.*;
import java.util.*;

/**
 * Creates rooms.
 */
public class Room {
    private String name;
    private List<Room> connectedRooms;
    private List<Objekkt> objects;
    private List<Chara> characters;
    private boolean locked;
    public Room(String name, String furnitureFile, String itemsFile, String charactersFile) {
        this.name = name;
        this.connectedRooms = new ArrayList<>();
        this.objects = new ArrayList<>();
        this.characters = new ArrayList<>();
        this.locked = false;
        loadFurniture(furnitureFile, itemsFile);
        loadCharacters(charactersFile);
    }

    /**
     * Loads furniture and other things into the room.
     * @param furnitureFile
     * @param itemsFile
     */
    private void loadFurniture(String furnitureFile, String itemsFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(furnitureFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 2) {
                    String roomName = parts[0].trim();
                    String objectName = parts[1].trim();

                    if (roomName.equals(this.name)) {
                        this.addObject(new Objekkt(objectName, this, itemsFile));
                    }
                }
            }
        } catch (IOException e) {}
    }

    /**
     * Loads characters into the room.
     * @param charactersFile
     */
    private void loadCharacters(String charactersFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(charactersFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 4) {
                    String charName = parts[0].trim();
                    String roomName = parts[1].trim();
                    String dialog = parts[2].trim();
                    String itemName = parts[3].trim();

                    if (roomName.equals(this.name)) {
                        this.addCharacter(new Chara(charName, this, dialog, itemName));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Chyba při načítání postav pro " + name + ": " + e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public void addConnection(Room room) {
        if (!connectedRooms.contains(room)) {
            connectedRooms.add(room);
        }
    }

    public List<Room> getConnectedRooms() {
        return connectedRooms;
    }

    public boolean isConnectedTo(Room room) {
        return connectedRooms.contains(room);
    }

    public void addObject(Objekkt obj) {
        objects.add(obj);
    }

    public List<Objekkt> getObjects() {
        return objects;
    }

    public void addCharacter(Chara character) {
        characters.add(character);
    }

    public List<Chara> getCharacters() {
        return characters;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}