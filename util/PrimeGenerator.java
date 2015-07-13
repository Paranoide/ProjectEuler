package util;

import java.util.ArrayList;
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

    private final List<Long> primes = new ArrayList<>();

    private long p = 0;
    private int index = 0;

    public PrimeGenerator()
    {
        this.primes.add(2L);
        this.primes.add(3L);
    }

    public List<Long> generateFirstNPrimes(int n)
    {
        while (this.primes.size() < n)
        {
            this.calcNextPrime();
        }


        List<Long> thePrimes = new ArrayList<>();
        thePrimes.addAll(this.primes);
        int tooMuch = thePrimes.size() - n;
        while (tooMuch > 0)
        {
            thePrimes.remove(thePrimes.size() - 1);
            tooMuch--;
        }
        
        return thePrimes;
    }

    public List<Long> generatePrimesSmallerThanN(long n)
    {
        while (this.primes.get(this.primes.size() - 1) < n)
        {
            this.calcNextPrime();
        }


        List<Long> thePrimes = new ArrayList<>();
        thePrimes.addAll(this.primes);

        int size = thePrimes.size();
        if (thePrimes.get(size - 2) >= n)
        {
            thePrimes.remove(size - 1);
            thePrimes.remove(size - 2);
        }
        else if (thePrimes.get(size - 1) >= n)
        {
            thePrimes.remove(size - 1);
        }
        
        return thePrimes;
    }
    
    public long next()
    {
        while (this.index >= this.primes.size())
        {
            calcNextPrime();
        }
        return this.primes.get(index++);
    }
    
    public boolean isPrime(long n)
    {
        long sqrt = (long)(Math.sqrt(n) + 1);
        this.generatePrimesSmallerThanN((long)(sqrt + 1));
        return this.isPrimeNumber(n);
    }
    
    private void calcNextPrime()
    {
        boolean done = false;
        while (!done)
        {
            p += 6;
            if (isPrimeNumber(p - 1))
            {
                this.primes.add(p - 1);
                done = true;
            }
            if (isPrimeNumber(p + 1))
            {
                this.primes.add(p + 1);
                done = true;
            }
        }
    }

    private boolean isPrimeNumber(long n)
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
        List<Long> factors = new ArrayList<>();

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
    
    
    public static boolean rudimentalIsPrime(long prime)
    {
        if (prime % 2 == 0)
        {
            return false;
        }
        else
        {
            int sqrt = (int)Math.sqrt(prime) + 1;
            for (int t = 3; t < sqrt; t += 2)
            {
                if (prime % t == 0)
                {
                    return false;
                }
            }
        }
        return true;
    }
}
