package Commands;

import GameMechanics.House;
import GameMechanics.Inventory;
import GameMechanics.Item;
import GameMechanics.Chara;
import GameMechanics.Room;
import GameMechanics.Quest;

import java.util.List;
import java.util.Scanner;

/**
 * Lets the user give a quest items to the characters in the game.
 */
public class Give extends Command {
    private House house;
    private Scanner scanner;

    public Give(House house) {
        this.house = house;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String execute() {
        Room currentRoom = house.getCurrentRoom();
        List<Chara> characters = currentRoom.getCharacters();
        Inventory inventory = house.getUserInventory();

        if (characters.isEmpty()) {
            return "V této místnosti není nikdo, komu bys mohl něco dát.";
        }

        System.out.println("Komu chceš dát předmět?");
        for (Chara character : characters) {
            System.out.println("- " + character.getName());
        }

        System.out.print("> ");
        String chosenName = scanner.nextLine().trim(); // .trim() Gets rid of spaces at the back and end of a String but doesnt interfere with the spaces in the string

        for (Chara character : characters) {
            if (character.getName().equalsIgnoreCase(chosenName)) {
                Quest quest = house.getActiveQuest();
                if (quest == null || !quest.getCharacterName().equals(character.getName())) { // checks if a quest exists or if the character he chose doesnt equal a character he has a quest from.
                    return character.getName() + ": Zatím jsem ti nezadával žádný úkol.";
                }

                System.out.println("Jaký předmět chceš dát?");
                for (Item item : inventory.getItems()) {
                    System.out.println("- " + item.getName());
                }

                System.out.print("> ");
                String chosenItem = scanner.nextLine().trim();

                for (Item item : inventory.getItems()) {
                    if (item.getName().equalsIgnoreCase(chosenItem)) {
                        if (item.getName().equalsIgnoreCase(quest.getRequiredItem())) {
                            inventory.getItems().remove(item);
                            quest.completeQuest();
                            house.setActiveQuest(null);
                            house.setCompletedQuests(house.getCompletedQuests() + 1);
                            house.end();
                            System.out.println(house.getCompletedQuests() + "" + house.getTotalCharactes());
                            return character.getName() + ": Děkuji! Úkol dokončen.";
                        } else {
                            return character.getName() + ": To není ten správný předmět.";
                        }
                    }
                }
                return "Tento předmět nemáš v inventáři.";
            }
        }
        return "Taková postava zde není.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}