import java.util.ArrayList;

/**
 * This class demonstrates a use case for author recognition.
 * There is no need to add any code to this class.
 * Just use it to test your project.
 * 
 * @author Rachel CO
 * @version May 2013
 */
public class Demo
{

    String trainingDir = "training-texts/";
    String testDir = "test-texts/";
    String signatureDir = "signature-files/";

    /**
     * Illustration of use case for author recognition:
     * 1. get previously learned signatures
     * 2. get test signatures of unknown authors
     * 3. compare each mystery author with the known signatures
     * 4. choose and report the closest match
     * Note that not all mystery authors will be correctly identified
     */
    public void demo() 
    {
        //step 1 read trained signautures
        //note a better soln would be read all files in a directory
        //foreveryfiein signatureDir
        ArrayList<Signature> trainingsigs = new ArrayList<Signature>();
        trainingsigs.add(new Signature(signatureDir+"austen.stats.txt"));
        trainingsigs.add(new Signature(signatureDir+"bronte.stats.txt"));
        trainingsigs.add(new Signature(signatureDir+"carroll.stats.txt"));
        trainingsigs.add(new Signature(signatureDir+"christie.stats.txt"));
        trainingsigs.add(new Signature(signatureDir+"dickens.stats.txt"));
        trainingsigs.add(new Signature(signatureDir+"dostoyevsky.stats.txt"));
        trainingsigs.add(new Signature(signatureDir+"doyle.stats.txt"));
        trainingsigs.add(new Signature(signatureDir+"joyce.stats.txt"));
        trainingsigs.add(new Signature(signatureDir+"twain.stats.txt"));

        //step 2 calculate the signatures of mystery texts
        ArrayList<Signature> mysteries = new ArrayList<Signature>();
        mysteries.add(new Signature("Mystery1",testDir+"mystery1.txt"));
        mysteries.add(new Signature("Mystery2",testDir+"mystery2.txt"));
        mysteries.add(new Signature("Mystery3",testDir+"mystery3.txt"));
        mysteries.add(new Signature("Mystery4",testDir+"mystery4.txt"));
        mysteries.add(new Signature("Mystery5",testDir+"mystery5.txt"));
        mysteries.add(new Signature("Mystery6",testDir+"mystery6.txt"));

        //step 3 compare test data with known signatures to guess the author
        String author = "Unknown";
        double closest, current;

        System.out.println("Answers = Jane Austen, Lewis Carroll, Charles Dickens");
        System.out.println("Agatha Christie, Mark Twain, James Joyce"); 
        //Important Note: if your code is correct, the first 4 guesses will be correct
        //but the last two will be wrong.  Think about why this happens.

        for (Signature mys : mysteries) {

            closest = mys.compareTo(trainingsigs.get(0));
            author = trainingsigs.get(0).getAuthorName();
            for (int i = 1; i < trainingsigs.size(); i++) {
                current = mys.compareTo(trainingsigs.get(i));
                if (current < closest) {
                    closest = current;
                    author = trainingsigs.get(i).getAuthorName();
                }
            }
            System.out.println("sig vs mys distance = " + closest + 
                " guess = " + author);
        }
    }
}
