import java.util.ArrayList;
/**
 * Utility class for analysing words, phrases and sentences in text.
 * 
 * @author Aaron Goldsworthy 
 * @version 1.0
 */
public class SentenceAnalyser
{
    
     /** 
     * Regular expressions defining separators for deconstructing sentences
     */
    public static final String SENTENCE_SEPARATORS = "[.?!]";
    public static final String PHRASE_SEPARATORS = "[,;:]";
    public static final String WORD_SEPARATORS = "[ .?!,;:]";
    /** PUNCTUATION is symbols that should be removed from the beginning and end 
     * (but not the middle) of individual words
     */
    public static final String PUNCTUATION =  " !\"',;:.-?)([]<>*#\n\t\r";
    //^{}~`| uneccesary to consider.
    
    
   /**
    * Split a sentence into its words and return the words in an array list
    * @param sentence  A string already parsed as a sentence
    * @return ArrayList<String> of cleaned up word tokens
    */
   public static ArrayList<String> getWordsFromSentence(String sentence) 
   {
       ArrayList<String> words = new ArrayList<String>();
       String[] slices = sentence.split(WORD_SEPARATORS);
       for(String word : slices)
       {
           word = cleanWord(word).toLowerCase();
           if(!word.isEmpty())
           {
                words.add(word);
           }
       }
       words.trimToSize();
       return words;
   }
    
   /**
    * Split a sentence into its phrases and retrun the phrases in an array list
    * @param sentence A string already parsed as a sentence
    * @return ArrayList<String> of phrases
    */
   public static ArrayList<String> getPhrasesFromSentence(String sentence)
   {
        ArrayList<String> phrases = new ArrayList<String>();
        String[] slices = sentence.split(PHRASE_SEPARATORS);
        for(String phrase : slices)
        {
            phrase = cleanWord(phrase).toLowerCase();
            if(!phrase.isEmpty())
            {
                 phrases.add(phrase);
            }
        }
        phrases.trimToSize();
        return phrases;
   }

   /**
    * Remove leading and trailing punctuation characters
    * from a String of a single word and convert to lower case
    * @param word  a String possibly including punctuation
    * @return String the same word with leading and trailing (but not middle)
    * punctuation removed
    */
   public static String cleanWord(String word)
   {
        //use a suggested method???
        //word = word.replaceFirst("^[\\p{Punct} &%\\$\\+/=@]+", "");
        //return word.replaceAll("[\\p{Punct} &%\\$\\+/=@]+$", "");
        if(word==null || word.isEmpty())
        {
            System.out.println("Error word is empty");
            return null;
        }
        word = word.replaceFirst("^[!\"',;:.\\-?)(\\[\\]<>*#\n\t\r _]+", "");
        return word.replaceAll("[!\"',;:.\\-?)(\\[\\]<>*#\n\t\r _]+$", "");
   }
    
   /**
    * Given an ArrayList of lines from a text file, create a new array list
    * in which each entry is a sentence from the file 
    * with SENTENCE_SEPARATORS removed but no other changes.
    * A sentence is defined to be a sequence of characters that 
    * (1) is terminated by (but doesn't include) the characters ! ? . or the end of the file, 
    * (2) excludes whitespace on either end, and 
    * (3) is not empty.
    */
   public static ArrayList<String> convertFileLinesToSentences(ArrayList<String> filelines)
   {
       ArrayList<String> sentences = new ArrayList<String>();
       String sentence = new String();
       String[] temp = new String[2];
       for(String line : filelines)
       {
           if(!line.isEmpty())
           {
               line = line.trim().concat(" ");
               sentence = sentence.concat(line);
               while(sentence.contains(".") || sentence.contains("?") || sentence.contains("!"))
               {
                   temp = sentence.split(SENTENCE_SEPARATORS, 2);
                   if(!temp[0].trim().isEmpty())
                   {
                       sentences.add(temp[0].trim());
                   }
                   sentence = temp[1];
               }
           }
       }
       if(!sentence.trim().isEmpty())
       {
           sentences.add(sentence.trim());
       }
       sentences.trimToSize();
       return sentences;
   }
}
