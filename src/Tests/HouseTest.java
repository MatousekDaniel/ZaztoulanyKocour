package Tests;

import GameMechanics.House;
import GameMechanics.Room;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HouseTest {

    House house = new House("RoomConnection.txt", "RoomObjekkts.txt", "ObjekktsItems.txt", "RoomCharacters.txt");

    @Test
    void testLoadRooms() {
        Map<String, Room> rooms = house.getRooms();
        assertNotNull(rooms);
        assertFalse(rooms.isEmpty(), "Místnosti by měly být načteny");
    }
    @Test
    void testCurrentRoom() {
        assertNotNull(house.getCurrentRoom(), "Aktuální místnost by neměla být null");
    }

    @Test
    void testTotalCharactersCount() {
        int count = house.totalCharactersCount("test_characters.txt");
        assertTrue(count >= 0, "Počet postav by neměl být záporný");
    }

}