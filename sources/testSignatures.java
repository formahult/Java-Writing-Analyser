import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class testCompareSignatures
 *
 * @author  Rachel CO
 * @version May 2013
 */
public class testSignatures
{

    String trainingDir = "training-texts/";
    String testDir = "test-texts/";
    String signatureDir = "signature-files/";

    /** Test default constructor */
    @Test
    public void testSignatureGivenVals()
    {
        double[] sigvalues = {4.34109, 0.07957, 0.03839, 16.0092, 2.68682};
        Signature sig = new Signature("An Author", sigvalues);
        assertEquals("An Author", sig.getAuthorName());
        assertEquals(sigvalues[0], sig.getAwl(), 0.0001);
        assertEquals(sigvalues[1], sig.getTtr(), 0.0001);
        assertEquals(sigvalues[2], sig.getHlr(), 0.0001);
        assertEquals(sigvalues[3], sig.getAsl(), 0.0001);
        assertEquals(sigvalues[4], sig.getAsc(), 0.0001);
    }

    /** Test calculated constructor for a known text */
    @Test
    public void testSignatureCalculatedVals()
    {
        String testauthor = "Jane Austen";
        String testtext = "training-texts/prideandprejudice.txt";
        Signature sig = new Signature(testauthor,testtext);
        assertEquals("Jane Austen", sig.getAuthorName());
        assertEquals("training-texts/prideandprejudice.txt", sig.getTextFileName());
        assertEquals(4.440819338177502, sig.getAwl(), 0.0001);
        assertEquals(0.056943040918788, sig.getTtr(), 0.0001);
        assertEquals(0.023755674253725358, sig.getHlr(), 0.0001);
        assertEquals(16.94563740146779, sig.getAsl(), 0.0001);
        assertEquals(2.490078825767872, sig.getAsc(), 0.0001);
    }

    /** 
     * Test constructor read from a non-existant text file.
     * The system should not crash with an uncaught exception.
     * Signature metric fields should be set to 0.
     */
    @Test
    public void testCalculatedSigNotFound()
    {
        Signature sig = new Signature("signature-files/romeoandjuliet.txt");
        assertEquals(0.0, sig.getAwl(), 0.0001);
        assertEquals(0.0, sig.getTtr(), 0.0001);
        assertEquals(0.0, sig.getHlr(), 0.0001);
        assertEquals(0.0, sig.getAsl(), 0.0001);
        assertEquals(0.0, sig.getAsc(), 0.0001);
    }

    /** Test constructor read from file */
    @Test
    public void testSavedSig()
    {
        Signature sig = new Signature("signature-files/dostoyevsky.stats.txt");
        double[] sigvalues = {  4.254889,
                                0.042113,
                                0.018226,
                               14.041249,
                                2.2565792 };
        assertEquals("Fyodor Dostoyevsky", sig.getAuthorName());
        assertEquals("training-texts/brotherskaramazov.txt", sig.getTextFileName());
        assertEquals(sigvalues[0], sig.getAwl(), 0.0001);
        assertEquals(sigvalues[1], sig.getTtr(), 0.0001);
        assertEquals(sigvalues[2], sig.getHlr(), 0.0001);
        assertEquals(sigvalues[3], sig.getAsl(), 0.0001);
        assertEquals(sigvalues[4], sig.getAsc(), 0.0001);

    }

    /** 
     * Test constructor read from a non-existant signature file.
     * The system should not crash with an uncaught exception.
     * Signature metric fields should be set to 0.
     */
    @Test
    public void testSavedSigNotFound()
    {
        Signature sig = new Signature("signature-files/shakespeare.stats.txt");
        assertEquals(0.0, sig.getAwl(), 0.0001);
        assertEquals(0.0, sig.getTtr(), 0.0001);
        assertEquals(0.0, sig.getHlr(), 0.0001);
        assertEquals(0.0, sig.getAsl(), 0.0001);
        assertEquals(0.0, sig.getAsc(), 0.0001);
    }

    /** Comparing identical signatures gives value 0.0 */
    @Test
    public void testCompareEmpty()
    {
        Signature sig1, sig2;
        double[] sigvalues = {4.34109, 0.07957, 0.03839, 16.0092, 2.68682};
        sig1 = new Signature("Some Author", sigvalues);
        sig2 = new Signature("Some Author", sigvalues);
        assertEquals(0.0, sig1.compareTo(sig2), 0.0001);
        assertEquals(0.0, sig2.compareTo(sig1), 0.0001);
    }

    @Test
    public void testCompareSameFile()
    {
        String sigfile = signatureDir+"christie.stats.txt";
        //get signatures from the same saved file
        Signature sig1 = new Signature(sigfile); 
        Signature sig2 = new Signature(sigfile);
        assertEquals(0.0, sig1.compareTo(sig2), 0.0001);
        assertEquals(0.0, sig2.compareTo(sig1), 0.0001);
    }

    @Test
    public void testCompareKnown()
    {
        Signature sig1, sig2;
        String sigfile =
            "signature-files/christie.stats.txt";
        String textfile =
            "training-texts/mysteriousaffair.txt";
        sig1 = new Signature(sigfile);
        sig2 = new Signature("Agatha Christie",textfile);
        assertEquals(0.0, sig1.compareTo(sig2), 0.0001);
        assertEquals(0.0, sig2.compareTo(sig1), 0.0001);
    }

}

