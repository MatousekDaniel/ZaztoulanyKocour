import Commands.Console;

import java.util.Scanner;

public class Start {
    public void startDialogue(){

        String answer;

        Scanner sc = new Scanner(System.in);

        System.out.println("Basic dialogue");
        System.out.println("Chcete pokracovat? ANO /-/ NE");
        answer = sc.nextLine();
        while(answer != "ANO" || answer != "NE") {
        }
        if(sc.nextLine() == "ANO"){
            House hous = new House("RoomConnection.txt");
            hous.start();
            Console console = new Console();
            console.start();
        } else if (sc.nextLine() == "NE") {
            System.out.println("Snad se brzy uvidime");
            System.exit(0);
        } else {
            System.out.println("Napiste ANO nebo NE.");
        }
    }
}
