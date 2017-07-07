import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

/**
 * TextFileManager is a small utility class with 
 * static methods to load and save text files.
 * See the Java tutorial
 * http://docs.oracle.com/javase/tutorial/essential/io/file.html
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TextFileManager
{

    /** 
     * Read a file into a list of its lines of text.
     * Do something reasonable if the file is not found.
     * @param filename  text file to be read
     */
    public static ArrayList<String> readFile(String filename)
    {
        List<String> data = new ArrayList<String>();
        ArrayList<String> text = new ArrayList<String>();
        
        try
        {
           data = Files.readAllLines(Paths.get(filename), Charset.defaultCharset());
           text.addAll(data);
        }
        catch(IOException e) 
        {
            System.out.println("Cannot Open File: " + e.getMessage());
            return null;
        }
        catch(SecurityException e)
        {
            System.out.println("Cannot Open File: " + e.getMessage());
            return null;
        }
		text.trimToSize();
        return text; //TODO add code here 
    }

    /**
     * Write the lines of text in an array list to a given filename.
     * Do something reasonable if the file can not be written.
     * @param filename  The filename to write to
     * @param text      An arraylist of text lines to be written
     */
    public static void writeFile(String filename, ArrayList<String> text)
    {
        try
        {
            Files.write(Paths.get(filename),text,Charset.defaultCharset(), 
                StandardOpenOption.CREATE);
        }
        catch(IOException e) 
        {
            System.out.println("Cannot Create File:" + e.getMessage());
        }
        catch(UnsupportedOperationException e)
        {
            System.out.println("Cannot Create File:" + e.getMessage());
        }
        catch(SecurityException e)
        {
            System.out.println("Cannot Create File:" + e.getMessage());
        }
        
    }
  
}
