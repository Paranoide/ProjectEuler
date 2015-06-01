package problem0027;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import util.PrimeGenerator;

/**
 *
 *
 *
 * @author Paranoide
 */
public class Problem0027
{

    private static int maxPrime = 0;
    private static PrimeGenerator pg = new PrimeGenerator();

    private static int currSize = 0;
    private static final List<Integer> currPrimes = new ArrayList<>();

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        maxPrime = 100;
        createSomePrimes(maxPrime);

        int a;
        int b;
        int currMaxN;
        int maxN = 0;
        int maxA = 0;
        int maxB = 0;
        
        for (b = 3; b < 1000; b += 2)
        {
            for (a = -999; a < 1000; a += 2)
            {
                currMaxN = maxNForConsecutivePrimes(a, b);
                if (currMaxN > maxN)
                {
                    maxN = currMaxN;
                    maxA = a;
                    maxB = b;
                }
            }
        }
        
        System.out.println("maxN: " + maxN);
        System.out.printf("a * b = %d * %d = %d\n", maxA, maxB, maxA*maxB);

        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static int maxNForConsecutivePrimes(int a, int b)
    {
        int maxN = 0;
        int result = quadFunction(a, b, maxN);
        while (isPrime(result))
        {
            maxN++;
            result = quadFunction(a, b, maxN);
        }
        return maxN;
    }

    private static int quadFunction(int a, int b, int n)
    {
        return n * n + a * n + b;
    }

    private static void createSomePrimes(int n)
    {
        System.out.printf("Creating primes up to %d ...\n", n);
        
        List<Long> primes = pg.generatePrimesSmallerThanN(n);

        for (int t = currSize; t < primes.size(); t++)
        {
            currPrimes.add((int) (long) primes.get(t));
        }

        currSize = currPrimes.size();
        
        System.out.printf("...done!\n");
    }

    private static boolean isPrime(int n)
    {
        boolean isPrime;
        if (n < maxPrime)
        {
            isPrime = containsPrime(n);
        }
        else
        {
            maxPrime *= 2;
            createSomePrimes(maxPrime);
            isPrime = isPrime(n);
        }

        return isPrime;
    }

    private static boolean containsPrime(int n)
    {
        return Collections.binarySearch(currPrimes, n) >= 0;
    }
}
