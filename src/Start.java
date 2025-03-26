import Commands.Console;
import GameMechanics.House;

import java.util.Scanner;

public class Start {
    public void startDialogue() {

        String answer;
        Scanner sc = new Scanner(System.in);

        System.out.println("Basic dialogue");
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