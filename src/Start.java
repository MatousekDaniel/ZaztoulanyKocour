import Commands.Console;

import java.util.Scanner;

public class Start {
    public void startDialogue(){

        String answer;

        Scanner sc = new Scanner(System.in);

        System.out.println("Basic dialogue");
        System.out.println("Chcete pokracovat? ANO /-/ NE");
        answer = sc.nextLine();
        while (!answer.equals("ANO") && !answer.equals("NE")){
            System.out.println("\u001B[35m" + answer + "\u001B[0m" + " neni platne!");
            System.out.println("Napiste ANO nebo NE.");
            answer = sc.nextLine();
            if(answer.equals("ANO") || answer.equals("NE")){
                break;
            }else{
            }
        }
        if(answer.equals("ANO")){
            GameMechanics.House house = new GameMechanics.House("RoomConnection.txt");
            house.start();
            Console console = new Console();
            console.start();
        } else if (answer.equals("NE")) {
            System.out.println("Snad se brzy uvidime! o/");
            System.exit(0);
        }
    }
}
