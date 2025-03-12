package Commands;

public class Quit extends Command{
    @Override
    public String execute() {

        System.out.println("Děkuji za hraní <3!");
        System.exit(0);
        return null;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
