import java.util.ArrayList;

/**
 * An object representing a complete command to be executed.
 * 
 * @author Aaron GOldsworthy 21108324 
 * @version 1.0
 */
public class Command
{
    // instance variables - replace the example below with your own
    private CommandWord command;
    private ArrayList<String> argument;

    /**
     * Constructor for objects of class Command
     */
    public Command(CommandWord commandWord, ArrayList<String> argument)
    {
        this.command=commandWord;
        this.argument=argument;
    }
    
    /**
     * getter methods for the command object 
     */
    public CommandWord getCommand(){return command;}
    
    public ArrayList<String> getArguments() {return argument;}
}
