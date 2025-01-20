package de.michel.projecteuler.problems0001_0050.problem0014;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * The following iterative sequence is defined for the set of positive integers:
<pre>
n → n/2 (n is even)
n → 3n + 1 (n is odd)
</pre>
 * Using the rule above and starting with 13, we generate the following
 * sequence:
 * <pre>13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1</pre>
 * It can be seen that this sequence (starting at 13 and finishing at 1)
 * contains 10 terms. Although it has not been proved yet (Collatz Problem),
 * it is thought that all starting numbers finish at 1.<br/>
 * Which starting number, under one million, produces the longest chain?<br/>
 * <br/>
 * NOTE: Once the chain starts the terms are allowed to go above one million.
 * 
 * @author micmeyer
 */
public class Problem0014
{

    private static final Map<Long, Integer> collatzMap = new HashMap<>();
    
    private static long longestCollatz = 0;
    private static int longestCount = 0;
    
    private static long totalSteps = 0;
    private static long stepsReadInMap = 0;
    private static long recursiveSteps = 0;

    public static void main(String[] args)
    {
        
        long time = System.currentTimeMillis();
        for (long c = 1; c < 1000000; c++)
        {
            int result = collatz(c);
            totalSteps += result;
            if (result > longestCount)
            {
                longestCollatz = c;
                longestCount = result;
            }
//            System.out.printf("%6d: %3d\n", c, result);
        }
        
        System.out.printf("Longest collatz: %d with %d steps.\n", longestCollatz, longestCount);
        System.out.println("stepsReadInMap: " + stepsReadInMap);
        System.out.println("totalSteps    : " + totalSteps);
        System.out.println("recursiveSteps:   " + recursiveSteps);
        
        System.out.println("Time: " + (System.currentTimeMillis()-time));
    }

    public static int collatz(long n)
    {
        recursiveSteps++;
        if (n == 1)
        {
            return 1;
        }

        long newN;
        if ((n & 1) == 0)
        {
            newN = n / 2;
        }
        else
        {
            newN = 3 * n + 1;
        }

        if (collatzMap.containsKey(newN))
        {
            int result = collatzMap.get(newN);
            
            stepsReadInMap += result;
            
            return result + 1;
        }
        else
        {
            int result = collatz(newN);

            collatzMap.put(newN, result);
            
            return result + 1;
        }
    }

    // 10
}
