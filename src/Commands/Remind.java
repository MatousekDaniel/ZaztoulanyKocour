package Commands;

import GameMechanics.House;
import GameMechanics.Quest;

public class Remind extends Command {
    private House house;

    public Remind(House house) {
        this.house = house;
    }

    @Override
    public String execute() {
        Quest activeQuest = house.getActiveQuest();

        if (activeQuest == null) {
            return "Nemáš žádný aktivní úkol.";
        }

        return "Úkol: Přines '" + activeQuest.getCharacterName() + "' '" + activeQuest.getRequiredItem() + "'.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}