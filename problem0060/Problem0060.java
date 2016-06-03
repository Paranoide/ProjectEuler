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

    private static final int N = 5;
    private static PrimeGenerator pg;
    private static List<Long> primes;
    
    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();
        
        long sum = 0;

        int[] indices = new int[N];
        for (int t = 0; t < N; t++)
        {
            indices[t] = -1;
        }
        indices[N-1] = N-2;
        
        pg = new PrimeGenerator();

        boolean found = false;
        while (!found)
        {
            indices[N-1]++;
            primes = pg.generateFirstNPrimes(indices[N-1]+1);
            found = find(indices, 0);
        }
        
        for (int t = 0; t < N; t++)
        {
            sum += primes.get(indices[t]);
            System.out.println(primes.get(indices[t]));
        }
        System.out.println("Sum: " + sum);
        

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }
    
    public static boolean find(int[] indices, int depth)
    {
        if (depth == N-1)
        {
            return true;
        }
        
        int start = 0;
        if (depth > 0)
        {
            start = indices[depth-1] + 1;
        }
        int lastIndex = indices[N-1];
        boolean success = false;
        for (int t = start; t < lastIndex - (N-1) + depth && !success; t++)
        {
            indices[depth] = t;
            if (checkConcatenations(indices, depth))
            {
                success = find(indices, depth+1);
            }
        }
        
        return success;
    }
    
    private static boolean checkConcatenations(int[] indices, int indexIndex)
    {
        boolean success = true;
        long p1, p2 = primes.get(indices[indexIndex]);
        for (int t = 0; t < indexIndex && success; t++)
        {
            p1 = primes.get(indices[t]);
            success = checkConcatenations(p1, p2);
        }
        
        if (success)
        {
            p1 = primes.get(indices[N-1]);
            success = checkConcatenations(p1, p2);
        }
        
        return success;
    }
    
    private static boolean checkConcatenations(long p1, long p2)
    {
        int p1Len = length(p1);
        int p2Len = length(p2);
        // p1 = 23, p2 = 113
        // p1Len = 2, p2Len = 3
        // concat1 = 23113 = 10^(p2Len)*p1 + p2 = 10^3*23 + 113 = 23000 + 113 = 23113
        // concat2 = 11323 = 10^(p1Len)*p2 + p1 = 10^2*113 + 23 = 11300 + 23  = 11323
        long concat1 = ((long)Math.pow(10, p2Len))*p1 + p2;
        long concat2 = ((long)Math.pow(10, p1Len))*p2 + p1;
        
        boolean isPrime1 = pg.isPrime(concat1);
        boolean isPrime2 = pg.isPrime(concat2);
        
        return isPrime1 && isPrime2;
    }
    
    private static int length(long l)
    {
        int length = 0;
        while (l != 0)
        {
            l /= 10;
            length++;
        }
        return length;
    }
}
