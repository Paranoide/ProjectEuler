package problem0026;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * A unit fraction contains 1 in the numerator. The decimal representation of
 * the unit fractions with denominators 2 to 10 are given:
 * <pre>
 * 1/2	= 0.5
 * 1/3	= 0.(3)
 * 1/4	= 0.25
 * 1/5	= 0.2
 * 1/6	= 0.1(6)
 * 1/7	= 0.(142857)
 * 1/8	= 0.125
 * 1/9	= 0.(1)
 * 1/10	= 0.1
 * </pre>
 * Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be
 * seen that 1/7 has a 6-digit recurring cycle.<br/>
 * <br/>
 * Find the value of d &lt; 1000 for which 1/d contains the longest recurring
 * cycle
 * in its decimal fraction part.
 *
 * @author Paranoide
 */
public class Problem0026
{

    public static void main(String[] args)
    {
        int max = 0;
        int maxN = 0;
        boolean found = false;
        for (int n = 999; n > 1 && !found; n--)
        {
            int cycleLen = getCycleCount(n);
            if (cycleLen > max)
            {
                max = cycleLen;
                maxN = n;
                if (max == n - 1)
                {
                    found = true;
                }
            }
        }
        
        System.out.printf("Max: %d (for n == %d)\n", max, maxN);
    }

    private static int getCycleCount(int n)
    {
        final Map<Integer, Integer> remainders = new HashMap<>();
        int counter = 0;
        
        int rem = 1 % n;
        
        while (!remainders.containsKey(rem))
        {
            counter++;
            
            remainders.put(rem, counter);
            
            rem = 10*rem % n;
        }
        
        int cycleLen = (counter + 1) - remainders.get(rem);
        if (rem == 0 && cycleLen == 1)
        {
            cycleLen = 0;
        }
                
        return cycleLen;
    }
}
