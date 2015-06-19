package problem0042;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * The nth term of the sequence of triangle numbers is given by, tn = ½*n*(n+1);
 * so the first ten triangle numbers are:
 * <pre>
 * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
 * </pre>
 * By converting each letter in a word to a number corresponding to its
 * alphabetical position and adding these values we form a word value. For
 * example, the word value for SKY is 19 + 11 + 25 = 55 = t_10. If the word
 * value
 * is a triangle number then we shall call the word a triangle word.<br/>
 * <br/>
 * Using words.txt, a 16K text file
 * containing nearly two-thousand common English words, how many are triangle
 * words?
 *
 * @author micmeyer
 */
public class Problem0042
{

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();
        
        File file = new File(System.getProperty("user.dir") + "/src/problem0042/words.txt");
        String[] words = readWords(file);
        
        int count = 0;
        
        for (String word: words)
        {
            if (isTriangleNumber(getWordValue(word)))
            {
                count++;
            }
        }
        
        System.out.println("Count: " + count);
        
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis()-time));
    }
    
    private static boolean isTriangleNumber(int n)
    {
        int tn = (int)Math.sqrt(2*n + 0.25);
        
        return (smallGauss(tn) == n);
    }
    
    private static int smallGauss(int n)
    {
        return (n*(n+1))/2;
    }
    
    private static int getWordValue(String word)
    {
        int value = 0;
        for (int t = 0; t < word.length(); t++)
        {
            value += (int)(word.charAt(t) - 'A' + 1);
        }
        return value;
    }

    private static String[] readWords(File file)
    {
        String[] names = null;

        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line = br.readLine();

            names = Arrays.stream(line.split(","))
                    .map(s -> s.replace("\"", ""))
                    .toArray(size -> new String[size]);

        }
        catch (IOException ioe)
        {
            System.err.println(ioe.getMessage());
        }

        return names;
    }

}
