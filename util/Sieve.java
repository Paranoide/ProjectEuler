package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author micmeyer
 */
public class Sieve
{

    private long currMaxPrime = 0;

    private List<Long> primes = new ArrayList<>();

    public Sieve(long maxPrime)
    {
        this.currMaxPrime = maxPrime;
//        List<Boolean> sieve = sieve(maxPrime);
//        for (int t = 2; t < sieve.size(); t++)
//        {
//            if (sieve.get(t))
//            {
//                primes.add((long) t);
//            }
//        }
        System.out.println(sieve2(maxPrime));
        
    }

    private static List<Boolean> sieve(long n)
    {
        List<Boolean> primes = new ArrayList<>();
        for (int t = 0; t < n + 2; t++)
        {
            primes.add(Boolean.TRUE);
        }
        int i = 4;
        while (i < (n + 2))
        {
            primes.remove(i);
            primes.add(i, Boolean.FALSE);
            i += 2;
        }

        for (int j = 3; j < (n + 2); j += 2)
        {
            if (primes.get(j))
            {
                i = 2 * j;
                while (i < (n + 2))
                {
                    primes.remove(i);
                    primes.add(i, Boolean.FALSE);
                    i += j;
                }
            }
        }

        return primes;
    }

    private static List<Long> sieve2(long n)
    {
        List<Long> primes = new ArrayList<>();
        for (long t = 2; t < n; t++)
        {
            primes.add(t);
        }
        long i = 4;
        while (i < n)
        {
            primes.remove(Collections.binarySearch(primes, i));
            i += 2;
        }

        int index = 1;
        int remIndex;
        long p;
        while (index < primes.size())
        {
            p = primes.get(index);

            i = 2 * p;
            while (i < n)
            {
                remIndex = Collections.binarySearch(primes, i);
                if (remIndex >= 0)
                {
                    primes.remove(remIndex);
                }
                i += p;
            }
            index++;
        }

        return primes;
    }

    public static void main(String[] args)
    {
//        System.out.println(new PrimeGenerator().generatePrimesSmallerThanN(1000000));
        Sieve sieve = new Sieve(300000);
//        System.out.println(sieve.primes);

    }
}
