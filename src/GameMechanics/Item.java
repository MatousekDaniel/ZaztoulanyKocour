package GameMechanics;

public class Item {
    private String name;
    private Objekkt objekkt;

    public Item(String name, Objekkt objekkt) {
        this.name = name;
        this.objekkt = objekkt;
    }

    public String getName() {
        return name;
    }

    public Objekkt getObjekkt() {
        return objekkt;
    }
}