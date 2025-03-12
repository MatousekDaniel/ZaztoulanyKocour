package Commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Console {
    private boolean exit = false;
    private HashMap<String, Command> map = new HashMap<>();
    public static String allCommands = "allCommands.txt";
    public void inicializace(){
        map.put("jdi", new Go());
    }

    private Scanner sc = new Scanner(System.in);
    private void exec(){
        System.out.print(">>");
        String comm = sc.nextLine();
        comm = comm.trim();
        comm = comm.toLowerCase();
        saveCommands(comm);
        if(map.containsKey(comm)){
            System.out.println(">> "+map.get(comm).execute());
            exit = map.get(comm).exit();
        }else{
            System.out.println(">> Příkaz je neplatný, pro seznam příkazů napište ´Příkazy´.");
        }
    }

    public void start(){
        inicializace();
        try{
            resetAllCommands();
            do{
                exec();
            }while(!exit);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void saveCommands(String prikaz){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(allCommands,true))){
            bw.write(prikaz);
            bw.newLine();
        }catch(Exception e){

        }
    }
    private void resetAllCommands(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(allCommands,false))){
        }catch(Exception e){

        }
    }
}