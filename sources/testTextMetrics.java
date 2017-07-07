import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the utility class TextMetrics.
 *
 * @author  Rachel CO
 * @version May 2013
 */
public class testTextMetrics
{
 
    @Test
    public void testAWL() 
    { 
        HashMap<String,Integer> test = new HashMap<String,Integer>();
        test.put("james",1);//5
        //test.put("fennimore",1); //9
        test.put("fennimore",3); 
        test.put("cooper",1);//6
        test.put("peter",1);//5
        test.put("paul",1); //4
        test.put("and",1);//3
        test.put("Mary",1);//4
        assertEquals(/*5.14285714286*/6, TextMetrics.averageWordLength(test), 0.0001);
    }
    
    @Test
    public void testTTR()
    {   //"James Fennimore Cooper\n Peter, Paul, and Mary\n James Gosling";
        HashMap<String,Integer> test = new HashMap<String,Integer>();
        test.put("james",2);
        test.put("fennimore",1); 
        test.put("cooper",1);
        test.put("peter",1);
        test.put("paul",1); 
        test.put("and",1);
        test.put("Mary",1);
        test.put("Gosling",1);
        assertEquals(0.88888, TextMetrics.typeTokenRatio(test), 0.0001);
    }

    @Test
    public void testHLR()
    {   HashMap<String,Integer> test = new HashMap<String,Integer>();
        test.put("james",2);
        test.put("fennimore",1); 
        test.put("cooper",1);
        test.put("peter",1);
        test.put("paul",1); 
        test.put("and",1);
        test.put("Mary",1);
        test.put("Gosling",1);
        assertEquals(0.777777777778, TextMetrics.hapaxLegomanaRatio(test), 0.0001);
    }
    
    @Test
    public void testASL()
    {   ArrayList<String> test = new ArrayList<String>();
        test.add("The time has come, the Walrus said");
        test.add("To talk of many things: of shoes - and ships - and sealing wax,");
        test.add("Of cabbages; and kings."); //23
        test.add("And why the sea is boiling hot;");
        test.add("and whether pigs have wings."); //12
        ArrayList<String> test2 = SentenceAnalyser.convertFileLinesToSentences(test);
        assertEquals(17.5, TextMetrics.averageSentenceLength(test2), 0.0001);
    }
    
    @Test
    public void testASC()
    {   ArrayList<String> test = new ArrayList<String>();
        test.add("The time has come, the Walrus said");
        test.add("To talk of many things: of shoes - and ships - and sealing wax,");
        test.add("Of cabbages; and kings."); //5
        test.add("And why the sea is boiling hot;");
        test.add("and whether pigs have wings."); //2
        ArrayList<String> test2 = SentenceAnalyser.convertFileLinesToSentences(test);
        assertEquals(3.5, TextMetrics.averageSentenceComplexity(test2), 0.0001);
    }
    
    @Test
    public void testNumberOfPhrasesZero() 
    {
        assertEquals(0,TextMetrics.numberOfPhrases(""));
    }

    @Test
    public void testNumberOfPhrasesOne() 
    {
        assertEquals(1,TextMetrics.numberOfPhrases("Here is a single phrase sentence."));
    }

    @Test
    public void testNumberOfPhrasesSeven() 
    {
        //Here is a more complex sentence from Mark Twain
        String s = "And next time Jim told it he said they rode him down to New Orleans; and, after that, every time he told it he spread it more and more, till by and by he said they rode him all over the world, and tired him most to death, and his back was all over saddle-boilss.";;
        assertEquals(7,TextMetrics.numberOfPhrases(s));
    }
    
    
}
