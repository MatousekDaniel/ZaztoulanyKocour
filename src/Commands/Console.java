package Commands;

import GameMechanics.House;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Console {
    private boolean exit = false;
    private HashMap<String, Command> map = new HashMap<>();
    public static String allCommands = "CommHistory.txt";
    private House house;

    // Konstruktor pro předání objektu House
    public Console(House house) {
        this.house = house;
    }

    // Inicializace příkazů
    public void inicializace() {
        map.put("příkazy", new Comms());
        map.put("jdi", new Go(house));
        map.put("opustit", new Quit());
        map.put("prozkoumat", new Explore(house));
        map.put("prohlédnout", new Look(house));
        map.put("vzít", new Take(house));
        map.put("promluv", new Talk(house));
        map.put("dej", new Give(house));
        map.put("připomenout", new Remind(house));
    }

    private Scanner sc = new Scanner(System.in);

    private void exec() {
        System.out.print(">> ");
        String comm = sc.nextLine();
        saveCommands(comm);

        if (map.containsKey(comm)) {
            System.out.println(">> " + map.get(comm).execute());
            exit = map.get(comm).exit();
        } else {
            System.out.println(">> Příkaz je neplatný, pro seznam příkazů napište ´Příkazy´.");
        }
    }


    public void start() {
        inicializace();
        try {
            resetAllCommands();
            do {
                exec();
            } while (!exit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private void saveCommands(String prikaz) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(allCommands, true))) {
            bw.write(prikaz);
            bw.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void resetAllCommands() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(allCommands, false))) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}