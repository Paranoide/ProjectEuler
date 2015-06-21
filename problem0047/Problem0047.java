package problem0047;

import java.util.List;
import util.PrimeGenerator;

/**
 *
 * The first two consecutive numbers to have two distinct prime factors are:
 * <pre>
 * 14 = 2 × 7
 * 15 = 3 × 5
 * </pre>
 * The first three consecutive numbers to have three distinct prime factors are:
 * <pre>
 * 644 = 2² × 7 × 23
 * 645 = 3 × 5 × 43
 * 646 = 2 × 17 × 19.
 * </pre>
 * Find the first four consecutive integers to have four distinct prime factors.
 * What is the first of these numbers?
 *
 * @author micmeyer
 */
public class Problem0047
{

    private static final int N = 4;

    private static final int[] LAST_N_FACTOR_COUNTS = new int[N];

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        List<Long> list;
        boolean found = false;
        int n = 1, count;
        
        while (!found)
        {
            list = PrimeGenerator.getPrimeFactors(n);
            count = countDistinctPrimeFactors(list);
            enqueueFactorCount(count);
            if (lastNFactorsAllEqualToK(N))
            {
                System.out.println("First number: " + (n - N + 1));
                found = true;
            }
            n++;
        }

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static void enqueueFactorCount(int count)
    {
        int len = LAST_N_FACTOR_COUNTS.length;
        for (int t = 0; t < len - 1; t++)
        {
            LAST_N_FACTOR_COUNTS[t] = LAST_N_FACTOR_COUNTS[t + 1];
        }
        LAST_N_FACTOR_COUNTS[len - 1] = count;
    }

    private static boolean lastNFactorsAllEqualToK(int k)
    {
        for (int t = 0; t < LAST_N_FACTOR_COUNTS.length; t++)
        {
            if (LAST_N_FACTOR_COUNTS[t] != k)
            {
                return false;
            }
        }
        return true;
    }

    private static int countDistinctPrimeFactors(List<Long> list)
    {
        int count = 0;
        Long lastItem = 0L;

        for (Long item : list)
        {
            if (item.compareTo(lastItem) > 0)
            {
                count++;
            }
            lastItem = item;
        }
        return count;
    }

}
