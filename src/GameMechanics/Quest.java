package GameMechanics;

public class Quest {
    private String characterName;
    private String requiredItem;
    private boolean completed;

    public Quest(String characterName, String requiredItem) {
        this.characterName = characterName;
        this.requiredItem = requiredItem;
        this.completed = false;
    }

    public String getCharacterName() {
        return characterName;
    }

    public String getRequiredItem() {
        return requiredItem;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void completeQuest() {
        this.completed = true;
    }
    public boolean isActive() {
        return !completed;
    }
}