import java.util.ArrayList;
import java.util.HashMap;

/**
 * Signature features for a String of text
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Signature
{

    /**
     * Weights used for comparing signatures.
     * Each weight corresponds to one of the Signature feature metrics:
     * awl, ttr, hlr, asl and asc (respectively).
     * Do not change these weights.
     * If you want to experiment with different weights then define an 
     * additional constant.
     */
    public static final double[] WEIGHTS = { 11, 33, 50, 0.4, 4 };

    private String authorName; //author of this text
    private String textFileName; //file containing the text
    private double awl; //average word length;
    private double ttr; //type token ratio
    private double hlr; //Hapax Legomana Ratio 
    private double asl; //average sentence length (in words)
    private double asc; //average sentence complexity

    //store word frequencies in a dictionary
    private HashMap<String,Integer> dictionary; 

    /**
     * Constructor to create a signature from given metric values
     * @param authorname String of the author
     * @param metrics double array of values for 5 metrics:
     * awl, ttr, hlr, asl and asc values respectively
     */
    public Signature(String authorName, double[] metrics)
    {
        this.authorName = authorName;
        this.textFileName = new String();
        awl = metrics[0];
        ttr = metrics[1];
        hlr = metrics[2];
        asl = metrics[3];
        asc = metrics[4];
        dictionary = new HashMap<String, Integer>();
    }

    /**
     * Constructor for learning a Signature for the text in a given file.
     * If file is not found then the signature values are the default values.
     * Signature String field values are given by the parameters.
     * Signature feature values are calculated using metrics methods from the 
     * TextMetrics utility class.
     * @param String name of the author of text 
     * @param String text a file containing example text written by this author
     */
    public Signature(String authorName, String textFileName)
    {
        this.authorName = authorName;
        this.textFileName = textFileName;
        ArrayList<String> text = TextFileManager.readFile(textFileName);
        ArrayList<String> sentences = new ArrayList<String>();
        if(text != null)
        {
            sentences = SentenceAnalyser.convertFileLinesToSentences(text);
            dictionary = new HashMap<String, Integer>();
            makeDictionary(sentences);
            awl = TextMetrics.averageWordLength(dictionary);
            ttr = TextMetrics.typeTokenRatio(dictionary);
            hlr = TextMetrics.hapaxLegomanaRatio(dictionary);
            asl = TextMetrics.averageSentenceLength(sentences);
            asc = TextMetrics.averageSentenceComplexity(sentences);
        }
        else
        {
            System.out.println("Setting default values of 0.0");
            authorName = new String();
            textFileName = new String();
            awl = 0.0;
            ttr = 0.0;
            hlr = 0.0;
            asl = 0.0;
            asc = 0.0;
            dictionary = new HashMap<String, Integer>();
        }
    }


    /**
     * Constructor reads a signature that was previously saved in a text file.
     * If file is not found then the signature values left as default values.
     * @param String filename name of file to read the signature details from.
     */
    public Signature(String filename)
    {
        ArrayList<String> data = TextFileManager.readFile(filename);
        if(data!=null && data.size()==7)
        {
            authorName = data.get(0);
            textFileName = data.get(1);
            awl = Double.valueOf(data.get(2)).doubleValue();
            ttr = Double.valueOf(data.get(3)).doubleValue();
            hlr = Double.valueOf(data.get(4)).doubleValue();
            asl = Double.valueOf(data.get(5)).doubleValue();
            asc = Double.valueOf(data.get(6)).doubleValue();
            dictionary = new HashMap<String, Integer>();
        }
        else
        {
            System.out.println("Stat file was corrupt");
            System.out.println("Setting default values of 0.0");
            authorName = new String();
            textFileName = new String();
            awl = 0.0;
            ttr = 0.0;
            hlr = 0.0;
            asl = 0.0;
            asc = 0.0;
            dictionary = new HashMap<String, Integer>();
        }
    }

    //getter methods for the signature fields
    public String getAuthorName() { return authorName; }

    public String getTextFileName() { return textFileName; }

    public double getAwl() { return awl; }

    public double getTtr() { return ttr; }

    public double getHlr() { return hlr; }

    public double getAsl() { return asl; }

    public double getAsc() { return asc; }
    
    //no need for ths method once complete.
    public HashMap<String, Integer> getDictionary() {return dictionary;}

    /** 
     * makeDictionary is used to help create a signature.
     * Get all the words from a list of Strings, each representing a sentence.
     * Save the words and their frequencies to the dictionary field.
     * (Hint: write helper methods as required for creating the dictionary)
     * @param   newtext an ArrayList of sentences
     */
    public void makeDictionary(ArrayList<String> sentencetext)
    {
        for(String sentence : sentencetext)
        {
            ArrayList<String> words = SentenceAnalyser.getWordsFromSentence(sentence);
            for(String word : words)
            {
                if(dictionary.containsKey(word))
                {
                    dictionary.get(word);
                    dictionary.put(word, dictionary.get(word) + 1);
                }
                else
                {
                    dictionary.put(word,1);
                }
            }
        }
    }
    
    /**
     * Save this signature object to a text file for later use
     * @param String filename name of file to write to
     * exception thrown if file can not be created
     */
    public void writeSignature(String filename)
    {
        TextFileManager.writeFile(filename, makeArrayListOfSig());
    }

    /** 
     * Helper method for writeSignature that converts a Signature object 
     * into an ArrayList of Strings for output, using one field per line.
     * See example signature files for the 7 line format.
     */
    private ArrayList<String> makeArrayListOfSig() 
    {
        ArrayList<String> output = new ArrayList<String>();
        output.add(authorName);
        output.add(textFileName);
        output.add(Double.toString(awl));
        output.add(Double.toString(ttr));
        output.add(Double.toString(hlr));
        output.add(Double.toString(asl));
        output.add(Double.toString(asc));
        output.trimToSize();
        return output;
    }

    /**
     * Compares this signature with another Signature object.
     * @param sigA a Signature to be compared to this one.
     * @return a non-negative real number indicating the similarity of two 
     * linguistic signatures. The smaller the number the more similar the 
     * signatures. Zero indicates identical signatures.
     * The comparison function uses the global WEIGHT 
     * list of multiplicative weights to apply to each of the five
     * linguistic features (order )
     */
    public double compareTo(Signature sigA) 
    {
        double difference = 0.0;
        difference += Math.abs(this.awl-sigA.getAwl())*WEIGHTS[0];
        difference += Math.abs(this.ttr-sigA.getTtr())*WEIGHTS[1];
        difference += Math.abs(this.hlr-sigA.getHlr())*WEIGHTS[2];
        difference += Math.abs(this.asl-sigA.getAsl())*WEIGHTS[3];
        difference += Math.abs(this.asc-sigA.getAsc())*WEIGHTS[4];
        difference = difference/5.0;
        return difference;
    }
    
    public void merge(Signature sigA)
    {
        this.awl=(this.awl+sigA.getAwl())/2;
        this.ttr=(this.ttr+sigA.getTtr())/2;
        this.hlr=(this.hlr+sigA.getHlr())/2;
        this.asl=(this.asl+sigA.getAsl())/2;
        this.asc=(this.asc+sigA.getAsc())/2;
    }

}
