package Commands;

import GameMechanics.Chara;
import GameMechanics.House;
import GameMechanics.Quest;
import GameMechanics.Room;

import java.util.List;
import java.util.Scanner;

public class Talk extends Command {
    private House house;
    private Scanner scanner;

    public Talk(House house) {
        this.house = house;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String execute() {
        Room currentRoom = house.getCurrentRoom();
        List<Chara> characters = currentRoom.getCharacters();

        if (characters.isEmpty()) {
            return "V této místnosti není nikdo, s kým bys mohl mluvit..";
        }

        System.out.println("S kým chceš mluvit?");
        for (Chara character : characters) {
            System.out.println("- " + character.getName());
        }

        System.out.print("> ");
        String chosenName = scanner.nextLine().trim();

        for (Chara character : characters) {
            if (character.getName().equalsIgnoreCase(chosenName)) {
                Quest quest = character.getQuest();

                if (house.getActiveQuest() != null) {
                    return character.getName() + ": Už máš aktivní úkol, nejdřív ho dokonči.";
                }

                if (quest.isCompleted()) {
                    System.out.println(character.getName() + ": " + character.talk());
                } else {
                    System.out.println(character.getName() + ": " + character.talk());
                    System.out.print("Přijmout úkol? (ano/ne): ");
                    String response = scanner.nextLine().trim();

                    if (response.equalsIgnoreCase("ano")) {
                        house.setActiveQuest(quest);
                        return character.getName() + ": Děkuji moc!";
                    } else if (response.equalsIgnoreCase("ne")) {
                        return character.getName() + ": Dobře, třeba jindy.";
                    } else {
                        System.out.println("Neplatná odpověď");
                        return "";
                    }
                }
            }
        }

        return "Taková postava zde není.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}