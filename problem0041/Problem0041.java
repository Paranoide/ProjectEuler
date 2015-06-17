package problem0041;

import java.util.ArrayList;
import java.util.List;
import util.Digits;
import util.PrimeGenerator;

/**
 *
 * We shall say that an n-digit number is pandigital if it makes use of all the
 * digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital and is
 * also prime.
 *
 * What is the largest n-digit pandigital prime that exists?
 *
 * @author micmeyer
 */
public class Problem0041
{

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        PrimeGenerator pg = new PrimeGenerator();
        long result = -1;
        
        /*
         *  Note: Nine numbers cannot be done (1+2+3+4+5+6+7+8+9=45 => always dividable by 3)
         *  Note: Eight numbers cannot be done (1+2+3+4+5+6+7+8=36 => always dividable by 3)
         */
        
        for (int t = 7; t > 0 && result < 0; t--)
        {
            List<Long> pandigitals = createAllNPandigitals(t);
            long pan;
            for (int index = pandigitals.size() - 1; index >= 0 && result < 0; index--)
            {
                pan = pandigitals.get(index);
                if (pg.isPrime(pan))
                {
                    result = pan;
                }
            }
        }

        System.out.println("Result: " + result);
        
        System.out.println("Time: " + (System.currentTimeMillis() - time));

    }

    private static List<Long> createAllNPandigitals(int n)
    {
        List<Long> result = new ArrayList<>();
        createAllNPandigitals(n, new ArrayList<>(), result);
        return result;
    }

    private static void createAllNPandigitals(int n, List<Integer> digits, List<Long> result)
    {

        if (digits.size() == n)
        {
            result.add(Digits.digitListToLong(digits));
        }
        else
        {
            for (int d = 1; d <= n; d++)
            {
                if (!digits.contains(d))
                {
                    digits.add(d);
                    createAllNPandigitals(n, digits, result);
                    digits.remove((Integer) d);
                }
            }
        }
    }
}
