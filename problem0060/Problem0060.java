package problem0060;

import java.util.Arrays;
import java.util.List;
import util.PrimeGenerator;

/**
 *
 * The primes 3, 7, 109, and 673, are quite remarkable. By taking any two primes
 * and concatenating them in any order the result will always be prime. For
 * example, taking 7 and 109, both 7109 and 1097 are prime. The sum of these
 * four primes, 792, represents the lowest sum for a set of four primes with
 * this property.<br/>
 * <br/>
 * Find the lowest sum for a set of five primes for which any two primes
 * concatenate to produce another prime.
 *
 * @author micmeyer
 */
public class Problem0060
{

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        final int N = 4;

        long maxPrime = 100;
        PrimeGenerator pg = new PrimeGenerator();
        List<Long> primes = pg.generatePrimesSmallerThanN(maxPrime);

        int[] indices = new int[N];
        for (int t = 0; t < N; t++)
        {
            indices[t] = t;
        }
        long[] diffs = new long[N];

        boolean found = false;
        int lowestIndex = 0;
        long prime;

        while (!found)
        {
            Arrays.fill(diffs, 0);
            for (int i = 0; i < N - 1; i++)
            {
                if (indices[i] != indices[i + 1] - 1)
                {
                    diffs[i] = primes.get(indices[i] + 1) - primes.get(indices[i]);
                }
            }
            diffs[N - 1] = primes.get(indices[N - 1] + 1) - primes.get(indices[N - 1]);

            lowestIndex = getIndexWithLowestDiff(diffs);

            if (lowestIndex >= 0)
            {
                indices[lowestIndex]++;
            }
            else
            {
                throw new RuntimeException("lowestIndex: " + lowestIndex
                        + " at indices: " + Arrays.toString(indices));
            }

            if (indices[N - 1] >= primes.size())
            {
                maxPrime *= 1.1;
                primes = pg.generatePrimesSmallerThanN(maxPrime);
            }
            
            if (areConcatenablePrimes(indices, primes, pg))
            {
                found = true;
            }

            found = true;
        }
        
        long sum = 0;
        long[] ps = new long[N];
        for (int t = 0; t < N; t++)
        {
            ps[t] = primes.get(indices[t]);
            sum += ps[t];
        }
        System.out.println(Arrays.toString(ps));
        System.out.println("Sum: " + sum);

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static int getIndexWithLowestDiff(long[] diffs)
    {
        int lowest = -1;
        long diff, lowestDiff = Long.MAX_VALUE;

        for (int t = 0; t < diffs.length; t++)
        {
            diff = diffs[t];
            if (diff > 0 && diff < lowestDiff)
            {
                lowestDiff = diff;
                lowest = t;
            }
        }

        return lowest;
    }

    private static boolean areConcatenablePrimes(int[] indices,
                                                List<Long> primes,
                                                PrimeGenerator pg)
    {
        int n = indices.length;
        long[] corrPrimes = new long[n];

        for (int t = 0; t < n; t++)
        {
            corrPrimes[t] = primes.get(indices[t]);
        }

        return corrPrimes[n - 1] == 0 ? null : corrPrimes;
    }

}
