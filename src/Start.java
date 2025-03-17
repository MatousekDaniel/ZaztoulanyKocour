import Commands.Console;
import GameMechanics.House;

import java.util.Scanner;

public class Start {
    public void startDialogue() {

        String answer;
        Scanner sc = new Scanner(System.in);

        System.out.println("Basic dialogue");
        System.out.println("Chcete pokracovat? ANO /-/ NE");
        answer = sc.nextLine();

        while (!answer.equals("ANO") && !answer.equals("NE")) {
            System.out.println("\u001B[35m" + answer + "\u001B[0m" + " neni platne!");
            System.out.println("Napiste ANO nebo NE.");
            answer = sc.nextLine();
        }

        if (answer.equals("ANO")) {
            // Vytvoření objektu House
            House house = new House("RoomConnection.txt", "RoomObjekkts.txt", "ObjekktsItems.txt");
            // Vytvoření objektu Console a předání objektu house
            Console console = new Console(house);
            console.start();  // Spuštění konzole pro interakci s uživatelem
        } else if (answer.equals("NE")) {
            System.out.println("Snad se brzy uvidime! o/");
            System.exit(0);
        }
    }
}