
/**
 * Enumeration class CommandWords - An enum containing all valid command words, 
 * plus one more for unknown words.
 * 
 * @author Aaron Goldsworthy 21108324
 * @version 1.0
 */
public enum CommandWord
{
    HELP("help"), QUIT("quit"), READ("read"), ANALYSE("analyse"), WRITE("write"), COMPARE("compare"),
    MERGE("merge"), LIST("list"), REMOVE("remove"), GUESS("guess"), UNKNOWN("?");
    
    private String commandString;
    
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    public String toString()
    {
        return commandString;
    }
}
