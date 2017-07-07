import java.util.HashMap;
import java.util.ArrayList;

/**
 * Write a description of class CommandWords here.
 * 
 * @author Aaron Goldsworthy 21108324 
 * @version 1.0
 */
public class CommandWords
{
    private HashMap<String, CommandWord> validCommands;

    /**
     * Constructor for objects of class CommandWords
     */
    public CommandWords()
    {
        validCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values())
        {
            if(command != CommandWord.UNKNOWN)
            {
                validCommands.put(command.toString(), command);
            }
        }
    }
    
    public CommandWord getCommand(String word)
    {
        CommandWord command = validCommands.get(word);
        if(command != null)
        {
            return validCommands.get(word);
        }
        return CommandWord.UNKNOWN;
    }

    /**
     * Returns a list of valid commands as an array list of strings.
     */
    public ArrayList<String> getValidCommands()
    {
        ArrayList<String> words = new ArrayList<String>();
        for(String word : validCommands.keySet())
        {
            words.add(word);
        }
        return words;
    }
}
