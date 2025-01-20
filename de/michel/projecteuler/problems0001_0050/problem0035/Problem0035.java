package de.michel.projecteuler.problems0001_0050.problem0035;

import java.util.Collections;
import java.util.List;
import de.michel.projecteuler.util.Digits;
import de.michel.projecteuler.util.PrimeGenerator;

/**
 *
 * The number, 197, is called a circular prime because all rotations of the
 * digits: 197, 971, and 719, are themselves prime.
 *
 * There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71,
 * 73, 79, and 97.
 *
 * How many circular primes are there below one million?
 *
 * @author micmeyer
 */
public class Problem0035
{

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();
        
        PrimeGenerator pg = new PrimeGenerator();
        List<Long> primes = pg.generatePrimesSmallerThanN(1000000);
        
        
        System.out.println("Time: " + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        
        long prime, p = 0;
        int len;
        List<Integer> digits;
        
        // Remove 2 and set count to 1 because of the containsEvenDigits method
        primes.remove(0);
        int count = 1;
        
        while (!primes.isEmpty())
        {
            prime = primes.remove(0);
            len = numberLength(prime) - 1;
            int len2 = len;
            digits = Digits.getDigitsAsList(prime);
            boolean circ = !containsEvenDigits(digits);
            
            while (len > 0 && circ)
            {
                Collections.rotate(digits, 1);
                p = Digits.digitListToLong(digits);
                circ = remove(primes, p) || prime == p;
                len--;
            }
            
            if (circ)
            {
                count += (prime == p) ? 1 : len2 + 1;
            }
        }
        
        System.out.println("Count: " + count);
        
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static int numberLength(long n)
    {
        int len = 0;
        while (n > 0)
        {
            len++;
            n /= 10;
        }
        return len;
    }

    private static boolean remove(List<Long> list, long toRemove)
    {
        int index = Collections.binarySearch(list, toRemove);
        if (index >= 0)
        {
            list.remove(index);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private static boolean containsEvenDigits(List<Integer> digits)
    {
        for (int d: digits)
        {
            if (d % 2 == 0)
            {
                return true;
            }
        }
        return false;
    }
}
