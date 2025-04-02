package GameMechanics;

import java.io.*;
import java.util.*;

/**
 * Pretty much the main class that is responsible for the map and logic of the game.
 */
public class House {
    private Map<String, Room> rooms;
    private Room currentRoom;
    private Inventory userInventory;
    private Quest activeQuest;
    private int totalCharactes;
    private int completedQuests;
    private Room secretRoom;

    public House(String roomFile, String furnitureFile, String itemsFile, String charactersFile) {
        this.rooms = new HashMap<>();
        loadRooms(roomFile, furnitureFile, itemsFile, charactersFile);
        this.userInventory = new Inventory();
        this.activeQuest = null;
    }

    /**
     * Loads in rooms that create a map (House).
     *
     * @param roomFile
     * @param furnitureFile
     * @param itemsFile
     * @param charactersFile
     */
    private void loadRooms(String roomFile, String furnitureFile, String itemsFile, String charactersFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(roomFile))) { // BufferReader reads files, FileReader opens files
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", "); //Splits a line into different parts using , | then puts them into a pole

                if (parts.length == 3) {
                    String room1 = parts[1].trim();
                    String room2 = parts[2].trim();


                    rooms.putIfAbsent(room1, new Room(room1, furnitureFile, itemsFile, charactersFile));
                    rooms.putIfAbsent(room2, new Room(room2, furnitureFile, itemsFile, charactersFile));


                    addConnection(room1, room2);
                }
            }

            secretRoom = rooms.get("tajnaMistnost");
            if (secretRoom != null) {
                secretRoom.setLocked(true);
            }

            currentRoom = rooms.getOrDefault("chodba1", rooms.values().stream().findFirst().orElse(null)); // makes chodba1 the starting room. If it doesnt exist, makes the first room it finds the starting room.

        } catch (IOException e) {
            System.err.println("Chyba při načítání místností: " + e.getMessage());
        }
    }

    /**
     * Connects rooms.
     * @param room1Name
     * @param room2Name
     */
    private void addConnection(String room1Name, String room2Name) {

        Room room1 = rooms.get(room1Name);
        Room room2 = rooms.get(room2Name);


        if (room1 != null && room2 != null) {
            room1.addConnection(room2);
            room2.addConnection(room1);
        } else {
            System.err.println("Propojení místností " + room1Name + " a " + room2Name + " neprobíhá, místnosti neexistují.");
        }
    }

    /**
     * Counts the amount of characters located in the game.
     * @param charactersFile
     * @return
     */
    public int totalCharactersCount(String charactersFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(charactersFile))) {
            while (reader.readLine() != null) {
                totalCharactes++;
            }
        } catch (IOException e) {
            System.err.println("Chyba při čtení souboru s postavami: " + e.getMessage());
        }
        return totalCharactes;
    }

    /**
     * After completing all the quests ends the game.
     */
    public void end(){
        secretRoom = rooms.get("tajnaMistnost");
        if (completedQuests == totalCharactes) {
            if (secretRoom != null) {
                secretRoom.setLocked(false);
                System.out.println("\u001B[33mTajná místnost se odemkla!\u001B[0m");
            }
        }else{

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

    public Inventory getUserInventory() {
        return userInventory;
    }

    public Quest getActiveQuest() {
        return activeQuest;
    }

    public void setActiveQuest(Quest quest) {
        this.activeQuest = quest;
    }

    public int getCompletedQuests() {
        return completedQuests;
    }

    public void setCompletedQuests(int completedQuests) {
        this.completedQuests = completedQuests;
    }

    public int getTotalCharactes() {
        return totalCharactes;
    }
}