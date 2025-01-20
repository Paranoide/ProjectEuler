package de.michel.projecteuler.problems0001_0050.problem0046;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import de.michel.projecteuler.util.PrimeGenerator;

/**
 *
 * It was proposed by Christian Goldbach that every odd composite number can be
 * written as the sum of a prime and twice a square.
 * <pre>
 * 9 = 7 + 2×12
 * 15 = 7 + 2×22
 * 21 = 3 + 2×32
 * 25 = 7 + 2×32
 * 27 = 19 + 2×22
 * 33 = 31 + 2×12
 * </pre>
 * It turns out that the conjecture was false.<br/>
 * <br/>
 * What is the smallest odd composite that cannot be written as the sum of a
 * prime and twice a square?
 *
 * @author micmeyer
 */
public class Problem0046
{

    private static List<Long> primes;

    private static int primeN = 1000;

    private static final PrimeGenerator pg = new PrimeGenerator();

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        primes = pg.generatePrimesSmallerThanN(primeN);

        long n = 3;
        boolean solved = false;

        while (!solved)
        {
            if (isComposite(n))
            {
                if (!isSumOfAPrimeAndTwiceASquare(n))
                {
                    System.out.println("Result: " + n);
                    solved = true;
                }
            }

            n += 2;
        }

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static boolean isSumOfAPrimeAndTwiceASquare(long n)
    {
        Iterator<Long> it = primes.iterator();
        it.next();
        long prime = it.next();

        while (prime < n && it.hasNext())
        {
            if (isTwiceASquare(n - prime))
            {
                return true;
            }
            prime = it.next();
        }
        return false;
    }

    private static boolean isTwiceASquare(long n)
    {
        n /= 2;
        double sqrt = Math.sqrt(n);
        return sqrt == (int) sqrt;
    }

    private static boolean isComposite(long n)
    {
        if (n > primes.get(primes.size() - 1))
        {
            primeN *= 2;
            primes = pg.generatePrimesSmallerThanN(primeN);
            return isComposite(n);
        }
        else
        {
            return !contains(n);
        }
    }

    private static boolean contains(long n)
    {
        return Collections.binarySearch(primes, n) >= 0;
    }

}
