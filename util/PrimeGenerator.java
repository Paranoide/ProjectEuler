package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * This class generates prime numbers successively.
 *
 * @author micmeyer
 */
public class PrimeGenerator
{
    private final List<Long> primes = new ArrayList<>();
    private final Map<Long, List<Long>> primeFactors = new HashMap<>();

    private long p = 5;
    private boolean pDeltaIsTwo = true;

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

        int size;
        while ((size = thePrimes.size()) > 0 && thePrimes.get(size - 1) >= n)
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
        long sqrt = (long) (Math.sqrt(n) + 1);
        this.generatePrimesSmallerThanN(sqrt + 1);
        return this.isPrimeNumber(n);
    }

    /**
     * Calculates the next prime number for the internal list.
     */
    private void calcNextPrime()
    {
        boolean done = false;
        while (!done)
        {
            if (isPrimeNumber(p))
            {
                this.primes.add(p);
                done = true;
            }

            this.nextP();
        }
    }

    private void nextP()
    {
        p += this.pDeltaIsTwo ? 2 : 4;
        this.pDeltaIsTwo = !this.pDeltaIsTwo;
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

    public List<Long> getPrimeFactors(long n)
    {
        List<Long> factors = new ArrayList<>();

        long sqrt = (long) Math.sqrt(n);

        while (n > 1 && n % 2 == 0)
        {
            factors.add(2L);
            n = n >> 1;
        }

        int primeListIndex = 1;

        this.generatePrimesSmallerThanN(sqrt + 1);
        if (this.primes.get(this.primes.size() - 1) <= sqrt)
        {
            this.calcNextPrime();
            this.calcNextPrime();
        }

        long _p = 3;
        while (_p <= sqrt && n > 1)
        {
            if (n % _p == 0)
            {
                factors.add(_p);
                n /= _p;
            }
            else
                _p = this.primes.get(++primeListIndex);
        }

        if (n > 1)
        {
            factors.add(n);
        }

        return factors;
    }

    public List<Long> getPrimeFactorsCached(final long N)
    {
        List<Long> factors = new ArrayList<>();

        long n = N;

        long sqrt = (long) Math.sqrt(n);

        while (n > 1 && n % 2 == 0)
        {
            factors.add(2L);

            n = n >> 1;

            List<Long> cachedFactors = this.primeFactors.get(n);
            if (cachedFactors != null)
            {
                factors.addAll(cachedFactors);
                primeFactors.put(N, factors);
                return factors;
            }
        }

        int primeListIndex = 1;

        this.generatePrimesSmallerThanN(sqrt + 1);
        if (this.primes.get(this.primes.size() - 1) <= sqrt)
        {
            this.calcNextPrime();
            this.calcNextPrime();
        }

        long _p = 3;
        while (_p <= sqrt && n > 1)
        {
            if (n % _p == 0)
            {
                factors.add(_p);
                n /= _p;

                List<Long> cachedFactors = this.primeFactors.get(n);
                if (cachedFactors != null)
                {
                    factors.addAll(cachedFactors);
                    primeFactors.put(N, factors);
                    return factors;
                }
            }
            else
            {
                _p = this.primes.get(++primeListIndex);
            }
        }

        if (n > 1)
        {
            factors.add(n);
        }

        primeFactors.put(N, factors);

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
            int sqrt = (int) Math.sqrt(prime) + 1;
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

    public static int calculatePhi(int n, List<Long> primeFactors)
    {
        long lastFac = 0, fac;
        double phi = n;
        for (int t = 0; t < primeFactors.size(); t++)
        {
            fac = primeFactors.get(t);
            if (fac != lastFac)
            {
                phi *= (1.0 - 1.0 / fac);
                lastFac = fac;
            }
        }
        return (int) (phi + 0.5);
    }
}
