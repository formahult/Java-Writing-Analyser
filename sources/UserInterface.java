import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

/*
 * remove spaces from hashmap keys
 */

/**
 * Write a description of class UserInterface here.
 * 
 * @author Aaron Goldsworthy 21108324
 * @version 1.0
 */
public  class UserInterface
{
    private static String title = "Writing Analyser";
    private static String version = "Version: 1.0";
    private static boolean quit;
    private static HashMap<String, Signature> sigs;
    
    public static void start()
    {
        sigs = new HashMap<String, Signature>();
        quit = false;
        printWelcome();
        while(!quit)
        {
            processCommand(); //do some work you lathargic machine.
        }
        System.out.println("Exiting...");
    }
    
    /**
     * Method to process commands into actions.
     */
    public static void processCommand()
    {
        Command command = InputReader.getCommand();
        ArrayList<String> arguments = new ArrayList<String>();
        switch(command.getCommand())
        {
            case UNKNOWN:
                System.out.println("Unknown Command");
                break;
            case HELP:
                printCommands();
                break;
            case QUIT:
                quit = true;
                break;
            case LIST:
                listSignatures();
                break;
            case REMOVE:
                arguments = command.getArguments();
                if(arguments.size()==1)
                {
                    if(sigs.containsKey(arguments.get(0)))
                    {
                        sigs.remove(arguments.get(0));
                    }
                    else
                    {
                        System.out.println(arguments.get(0)+" Is not a loaded signature");
                    }
                }
                else
                {
                    System.out.println("Use "+CommandWord.REMOVE+" [signature to remove]");
                }
                break;
            case READ:
                arguments = command.getArguments();
                if(arguments.size()==1)
                {
                    Signature sig = new Signature(arguments.get(0));
                    sigs.put(sig.getAuthorName().replaceAll(" ","_"), sig);
                }
                else
                {
                    System.out.println("Use "+CommandWord.READ+" [signature file location]");
                }
                break;
            case ANALYSE:
                arguments = command.getArguments();
                if(arguments.size()==2)
                {
                    sigs.put(arguments.get(0), new Signature(arguments.get(0),arguments.get(1)));
                }
                else
                {
                    System.out.println("Use "+CommandWord.ANALYSE+" [author] [txt file location]");
                }
                break;
            case WRITE:
                arguments = command.getArguments();
                if(arguments.size()==2)
                {
                    sigs.get(arguments.get(0)).writeSignature(arguments.get(1));
                }
                else
                {
                    System.out.println("Use "+CommandWord.WRITE+" [siganture to write] [file location]");
                }
                break;
            case COMPARE:
                arguments = command.getArguments();
                if(arguments.size()==2)
                {
                    Signature siga = sigs.get(arguments.get(0));
                    Signature sigb = sigs.get(arguments.get(1));
                    if(siga == null)
                    {
                        System.out.println(arguments.get(0)+" Is not a loaded signature");
                    }
                    if(sigb == null)
                    {
                        System.out.println(arguments.get(1)+" Is not a loaded signature");
                    }
                    else
                    {
                        System.out.println("Difference: "+siga.compareTo(sigb));
                    }
                }
                else
                {
                    System.out.println("Use "+CommandWord.COMPARE+" [signature 1] [signature 2]");
                }
                break;
            case MERGE:
                arguments = command.getArguments();
                if(arguments.size()==2)
                {
                    Signature siga = sigs.get(arguments.get(0));
                    Signature sigb = sigs.get(arguments.get(1));
                    if(siga == null)
                    {
                        System.out.println(arguments.get(0)+" Is not a loaded signature");
                    }
                    if(sigb == null)
                    {
                        System.out.println(arguments.get(1)+" Is not a loaded signature");
                    }
                    else
                    {
                        siga.merge(sigb);
                        sigs.put(arguments.get(0),siga);
                        sigs.remove(arguments.get(1));
                    }
                }
                else
                {
                    System.out.println("Use "+CommandWord.MERGE+" [signature 1] [signature 2]");
                }
                break;
            case GUESS:
                arguments = command.getArguments();
                if(arguments.size()==1)
                {
                    guess(arguments.get(0));
                }
                else
                {
                    System.out.println("Use "+CommandWord.GUESS+" [signature to compare to all others]");
                }
                break;
        }
    }

    /**
     *Prints the title and version number. only run on startup.
     */
    private static void printWelcome()
    {
        System.out.println(title);
        System.out.println(version);
        System.out.println("Type '"+CommandWord.HELP+"' for a list of commands");
    }
    
    /**
     * A method to print all available commands.
     */
    private static void printCommands()
    {
        for(CommandWord command : CommandWord.values())
        {
            System.out.println(command);
        }
    }
    
    /**
     * A method to list all loaded signatures.
     */
    private static void listSignatures()
    {
        Set<String> keySet = sigs.keySet();
        for(String key : keySet)
        {
            System.out.println(key);
        }
    }
    
    /**
     * A method to compare one signature to all other loaded signatures to pick the author.
     */
    private static void guess(String sigParam)
    {
        String guess = new String();
        double min = -1.0;
        double difference = 0.0;
        Signature siga = sigs.get(sigParam);
        Set<String> sigSet = sigs.keySet();
        System.out.println("Compared to:");
        for(String sig : sigSet)
        {
            if(!sigParam.contentEquals(sig))
            {
                difference = siga.compareTo(sigs.get(sig));
                System.out.println(sig+" Difference: "+difference);
                if(min > difference || min <0.0)
                {
                    min = difference;
                    guess = sig;
                }
            }
        }
        System.out.println("Best Guess: "+guess);
    }
}
