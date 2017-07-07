import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;
import java.util.Set;

/**
 * Utility class of methods to perform TextMetrics.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TextMetrics
{
    /**
     * Return the average length of all words in the dictionary
     */
    public static double averageWordLength(HashMap<String,Integer> dictionary) 
    {
        long numberWords = 0;
        long totalLength = 0;
        Set<String> words = dictionary.keySet();
        for(String word : words)
        {
            totalLength += word.length() * dictionary.get(word);
            numberWords += dictionary.get(word);
        }
        return (double)totalLength/(double)numberWords;
    }

    /** Return the type token ratio (TTR) for this text
     * using the dictionary of all words.
     * TTR is the number of different words divided by 
     * the total number of words.
     * The dictionary must
     */
    public static double typeTokenRatio(HashMap<String,Integer> dictionary) 
    {
        long words = dictionary.size();
        long totalWords = 0;
        Collection<Integer> values = dictionary.values();
        for(int freq : values)
        {
            totalWords+=freq;
        }
        return (double)words/(double)totalWords;
    }

    /**
     * Return the hapax_legomana ratio for this text.
     * This ratio is the number of words that occur exactly 
     * once divided by the total number of words.
     */
    public static double hapaxLegomanaRatio(HashMap<String,Integer> dictionary) 
    {
        long uniqueWords = 0;
        long totalWords = 0;
        Collection<Integer> values = dictionary.values();
        Set<String> keys = dictionary.keySet();
        for(String key : keys)
        {
            if(dictionary.get(key)==1)
            {
                uniqueWords++;
            }
        }
        for(int freq : values)
        {
            totalWords+=freq;
        }
        return (double)uniqueWords/(double)totalWords;
    }
    
    /**
     * Return the average number of words per sentence in text.
     * text is guaranteed to have at least one sentence.
     * Terminating punctuation defined as !?.
     * A sentence is defined as a non-empty string of non-terminating
     * punctuation surrounded by terminating punctuation
     * or beginning or end of file.
     */
    public static double averageSentenceLength(ArrayList<String> text) 
    {
        long numberOfSentences = text.size();
        long numberOfWords = 0;
        for(String sentence : text)
        {
            numberOfWords += SentenceAnalyser.getWordsFromSentence(sentence).size();
        }
        return (double)numberOfWords/(double)numberOfSentences;
    }

    /**
     * Return the average number of phrases per sentence.
     * Terminating punctuation defined as !?.
     * A sentence is defined as a non-empty string of non-terminating
     * punctuation surrounded by terminating punctuation
     * or beginning or end of file.
     * Phrases are substrings of a sentences separated by
     * one or more of the following delimiters ,;: 
     * - this one makes the difference to detection
     */
    public static double averageSentenceComplexity(ArrayList<String> text) 
    {
        long numberOfSentences = text.size();
        long numberOfPhrases = 0;
        for(String sentence : text)
        {
            numberOfPhrases += (double)numberOfPhrases(sentence);
        }
        return (double)numberOfPhrases/(double)numberOfSentences;
    }
    
         /**
     * Count number of non empty phrases in a sentence
     * by spliting the sentence on the PHRASE_SEPARATORS
     * and counting the number of parts
     * @param sentence  A string already parsed as a sentence
     * @return int count of the number of non-empty phrase parts from the split
     */
    public static int numberOfPhrases(String sentence) 
    {
        ArrayList<String> phrases = SentenceAnalyser.getPhrasesFromSentence(sentence);
        phrases.trimToSize();
        return phrases.size();
    }
 
}
