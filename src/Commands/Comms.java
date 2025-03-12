package Commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Comms extends Command{
    @Override
    public String execute() {
        System.out.println("Možné příkazy: ");

        try (BufferedReader br = new BufferedReader(new FileReader("AllCommands.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("\u001B[35mChyba při načítání souboru: \u001B[91m" + e.getMessage() + "\u001B[0m");
        }
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
