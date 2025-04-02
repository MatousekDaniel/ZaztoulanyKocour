package Commands;

/**
 * Basic structure for the other commands that extend Command.
 */
public abstract class Command {
    protected String comm;
    public void setComm(String comm){
        this.comm = comm;
    }
    public abstract String execute();
    public abstract boolean exit();

}
