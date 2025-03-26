package Tests;

import GameMechanics.Chara;
import GameMechanics.Objekkt;
import GameMechanics.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RoomTest {

    private Room room1 = new Room("chodba1", "RoomObjekkts.txt", "ObjekktsItems.txt", "RoomCharacters.txt");;
    private Room room2 = new Room("satna", "RoomObjekkts.txt", "ObjekktsItems.txt", "RoomCharacters.txt");;


    @Test
    void testAddConnection() {
        room1.addConnection(room2);
        assertTrue(room1.isConnectedTo(room2), "Místnosti by měly být propojeny");
        assertTrue(room2.isConnectedTo(room1), "Propojení by mělo být obousměrné");
    }

    @Test
    void testAddObject() {
        Objekkt obj = new Objekkt("stul", room1, "ObjekktsItmes.txt");
        room1.addObject(obj);
        assertTrue(room1.getObjects().contains(obj), "Objekt by měl být přidán do místnosti");
    }

    @Test
    void testAddCharacter() {
        Chara character = new Chara("Kluk", room1, "Ahoj", "klíč");
        room1.addCharacter(character);
        assertTrue(room1.getCharacters().contains(character), "Postava by měla být přidána do místnosti");
    }

}