package de.michel.projecteuler.problems0001_0050.problem0037;

import java.util.List;
import de.michel.projecteuler.util.PrimeGenerator;

/**
 *
 * The number 3797 has an interesting property. Being prime itself, it is
 * possible to continuously remove digits from left to right, and remain prime
 * at each stage: 3797, 797, 97, and 7. Similarly we can work from right to
 * left: 3797, 379, 37, and 3.
 *
 * Find the sum of the only eleven primes that are both truncatable from left to
 * right and right to left.
 *
 * NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
 *
 * @author micmeyer
 */
public class Problem0037
{
    
    private static PrimeGenerator pg;

    public static void main(String[] args)
    {
        pg = new PrimeGenerator();
        pg.next();
        pg.next();
        pg.next();
        pg.next();
        
        int count = 0;
        int sum = 0;
        long n = pg.next();
        
        while (count < 11)
        {
            if (isTruncatable(n)) 
            {
                sum += n;
                count++;
            }
            n = pg.next();
        }
        
        System.out.println("Sum: " + sum);
    }
    
    private static boolean isTruncatable(long n)
    {
        boolean isTrun = true;
        
        List<Long> primes = pg.generatePrimesSmallerThanN(n);
        
        long k = n / 10;
        while (isTrun && k > 0)
        {
            isTrun = primes.contains(k);
            k /= 10;
        }
        
        int len = String.valueOf(n).length();
        for (int t = 1; t < len && isTrun; t++)
        {
            k = n % power(10, t);
            isTrun = primes.contains(k);
        }
            
        return isTrun;
    }
    
    private static long power(int base, int exp)
    {
        return (int)Math.pow(base, exp);
    }

}
