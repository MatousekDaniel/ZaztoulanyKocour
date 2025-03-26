package GameMechanics;

public class Chara {
    private String name;
    private Room room;
    private String dialog;
    private String itemName;
    private Quest quest;

    public Chara(String name, Room room, String dialog, String itemName) {
        this.name = name;
        this.room = room;
        this.dialog = dialog;
        this.itemName = itemName;
        this.quest = new Quest(name, itemName);
    }

    public String getName() {
        return name;
    }

    public Room getRoom() {
        return room;
    }

    public Quest getQuest() {
        return quest;
    }

    public String talk() {
        if (quest.isCompleted()) {
            return ": Úkol již splněn.";
        }
        return name + ": " + dialog + "\nPřines mi '" + quest.getRequiredItem() + "'.";
    }
}