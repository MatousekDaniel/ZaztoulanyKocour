package GameMechanics;

import java.io.*;
import java.util.*;

public class Room {
    private String name;
    private List<Room> connectedRooms;
    private List<Objekkt> objects; // Seznam nábytku v místnosti

    public Room(String name, String furnitureFile, String itemsFile) {
        this.name = name;
        this.connectedRooms = new ArrayList<>();
        this.objects = new ArrayList<>();
        loadFurniture(furnitureFile, itemsFile); // Každá místnost načte svůj nábytek
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

    private void loadFurniture(String furnitureFile, String itemsFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(furnitureFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 2) {
                    String roomName = parts[0].trim();
                    String objectName = parts[1].trim();

                    if (roomName.equals(this.name)) {
                        this.addObject(new Objekkt(objectName, this, itemsFile)); // Předáme itemsFile
                    }
                }
            }
        } catch (IOException e) {}
    }

}