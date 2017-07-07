import java.util.ArrayList;
import java.util.Scanner;

/**
 * Write a description of class InputReader here.
 * 
 * @author Aaron Goldsworthy 21108324
 * @version 1.0
 */
public class InputReader
{
    /**
     * Get user input from the terminal
     * 
     * @return     An array of arguments 
     */
    public static ArrayList<String> getInput()
    {
        Scanner reader = new Scanner(System.in);
        System.out.print("> ");
        String inputLine = reader.nextLine().trim();
        String[] wordArray = inputLine.split(" ");
        ArrayList<String> words = new ArrayList<String>();
        for(String word : wordArray)
        {
            words.add(word);
        }
        words.trimToSize();
        return words;
    }
    
    public static Command getCommand()
    {
        CommandWords commandWords = new CommandWords();
        ArrayList<String> input = getInput();
        String command = input.get(0).toLowerCase();
        input.remove(0);
        input.trimToSize();
        return new Command(commandWords.getCommand(command), input);
    }
}
