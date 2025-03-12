package GameMechanics;

import java.util.*;

public class Room {
    private String name;
    private List<Room> connectedRooms;

    public Room(String name) {
        this.name = name;
        this.connectedRooms = new ArrayList<>();
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
}