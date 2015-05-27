package util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * This class generates prime numbers successively.
 *
 * @author micmeyer
 */
public class PrimeGenerator
{

    private List<Long> primes;

    public PrimeGenerator()
    {

    }

    public List<Long> generateFirstNPrimes(int n)
    {
        primes = new LinkedList<>();

        primes.add(2L);
        primes.add(3L);
        int primesFound = 2;
        long p = 0;

        while (primesFound < n)
        {
            p += 6;
            if (isPrime(p - 1))
            {
                primes.add(p - 1);
                primesFound++;
//                System.out.println(n);
            }
            if (isPrime(p + 1))
            {
                primes.add(p + 1);
                primesFound++;
//                System.out.println(n);
            }
        }

        return this.primes;
    }

    public List<Long> generatePrimesSmallerThanN(int n)
    {
        primes = new LinkedList<>();

        primes.add(2L);
        primes.add(3L);
        long p = 0;

        while ((p + 5) < n)
        {
            p += 6;
            if (isPrime(p - 1))
            {
                primes.add(p - 1);
//                System.out.println(p-1);
            }
            if (isPrime(p + 1))
            {
                primes.add(p + 1);
//                System.out.println(p+1);
            }
        }

        int size = this.primes.size();
        if (this.primes.get(size - 1) >= n)
        {
            this.primes.remove(size - 1);
        }

        return this.primes;
    }

    private boolean isPrime(long n)
    {

        long sqrt = (long) Math.sqrt(n);

        if (n < 2)
        {
            return false;
        }

        Iterator<Long> it = this.primes.iterator();
        long p;
        while (it.hasNext() && (p = it.next()) <= sqrt)
        {
            if (n % p == 0)
            {
                return false;
            }
        }

        return true;
    }

    public static List<Long> getPrimeFactors(long n)
    {
        List<Long> factors = new LinkedList<>();

        long sqrt = (long) Math.sqrt(n);

        while (n > 1 && n % 2 == 0)
        {
            factors.add(2L);
            n /= 2;
        }

        long p = 3;
        while (p <= sqrt && n > 1)
        {
            if (n % p == 0)
            {
                factors.add(p);
                n /= p;
            }
            else
            {
                p += 2;
            }
        }

        if (n > 1)
        {
            factors.add(n);
        }

        return factors;
    }
}
