import Commands.Console;
import GameMechanics.House;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Start {
    /**
     * Either starts or ends the game.
     * 1
     */
    public void startDialogue() {

        String answer;
        Scanner sc = new Scanner(System.in);

        try (BufferedReader br = new BufferedReader(new FileReader("StartDialogue.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("\u001B[35mChyba při načítání souboru: \u001B[91m" + e.getMessage() + "\u001B[0m");
        }

        System.out.println("Chcete pokracovat? ANO /-/ NE");
        answer = sc.nextLine().toUpperCase();

        while (!answer.equals("ANO") && !answer.equals("NE")) {
            System.out.println("\u001B[35m" + answer + "\u001B[0m" + " neni platne!");
            System.out.println("Napiste ANO nebo NE.");
            answer = sc.nextLine().toUpperCase();
        }

        if (answer.equals("ANO")) {
            House house = new House("RoomConnection.txt", "RoomObjekkts.txt", "ObjekktsItems.txt", "RoomCharacters.txt");
            house.totalCharactersCount("RoomCharacters.txt");
            Console console = new Console(house);
            console.start();
        } else if (answer.equals("NE")) {
            System.out.println("Snad se brzy uvidime! o/");
            System.exit(0);
        }
    }
}