
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Test methods of the SentenceAnalyser class
 *
 * @author  Rachel CO
 * @version May 2013
 */
public class testSentenceAnalyser
{


    @Test
    public void testGetWordsFromSentence() 
    {
        String hooray = "Hooray! Finally, we're done.";
        ArrayList<String> words = SentenceAnalyser.getWordsFromSentence(hooray);
        assertEquals("hooray",words.get(0));
        assertEquals("finally",words.get(1));
        assertEquals("we're",words.get(2));
        assertEquals("done",words.get(3));
    }



    @Test
    public void testCleanWordNoneNeeded()
    {
        assertEquals("clean",SentenceAnalyser.cleanWord("clean"));
    }

    @Test
    public void testCleanWordSomeNeeded()
    {
        assertEquals("fish-knife",SentenceAnalyser.cleanWord(" fish-knife!?"));
    }

    @Test
    public void testCleanWordApostrophe()
    {
        assertEquals("house-boy's",SentenceAnalyser.cleanWord("house-boy's_, "));
    }

        
    @Test
    public void testConvertFileLinesToSentences1()
    {
        ArrayList<String> filelines = new ArrayList<String>();
        filelines.add("This is the very, very,");
        filelines.add("first Sentence. Isn't");
        filelines.add("it? Yes ! !! This ");
        filelines.add("");
        filelines.add("");
        filelines.add("last bit :) is also a sentence; but");
        filelines.add("without a terminator: other than the end of the file");

        ArrayList<String> sentences = 
           SentenceAnalyser.convertFileLinesToSentences(filelines);
        assertEquals(4,sentences.size());
        assertEquals("This is the very, very, first Sentence",sentences.get(0));
        assertEquals("Isn't it",sentences.get(1));
        assertEquals("Yes",sentences.get(2));
        assertEquals("This last bit :) is also a sentence; but without a terminator: other than the end of the file",sentences.get(3));

    }

    @Test
    public void testConvertFileLinesToSentences2()
    {
        ArrayList<String> filelines = new ArrayList<String>();

        filelines.add("The quick brown fox, called Foxy, "); 
        filelines.add("jumped over the lazy dog!");
        filelines.add("Is this a sentence?   Yes!");

        ArrayList<String> sentences = SentenceAnalyser.convertFileLinesToSentences(filelines);
        assertEquals(3,sentences.size());
        assertEquals("The quick brown fox, called Foxy, jumped over the lazy dog",
            sentences.get(0));
        assertEquals("Is this a sentence",sentences.get(1));
        assertEquals("Yes",sentences.get(2));
    }
    
    @Test
    public void testGetWordsFromSentencewithAmpersand() 
    {
        String hooray = "Hooray! Finally it's over & we're done done done.";
        ArrayList<String> words = SentenceAnalyser.getWordsFromSentence(hooray);
        assertEquals("hooray",words.get(0));
        assertEquals("finally",words.get(1));
        assertEquals("it's",words.get(2));
        assertEquals("over",words.get(3));
        assertEquals("&",words.get(4));
        assertEquals("we're",words.get(5));
        assertEquals("done",words.get(6));
    }

}
