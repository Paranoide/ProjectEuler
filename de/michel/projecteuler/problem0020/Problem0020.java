package de.michel.projecteuler.problem0020;

import java.math.BigInteger;

/**
 *
 * n! means <code>n × (n − 1) × ... × 3 × 2 × 1</code><br/>
 *
 * For example, <code>10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800</code>,
 * and the sum of the digits in the number 10! is
 * <code>3 + 6 + 2 + 8 + 8 + 0 + 0 = 27</code>.
 * <br/>
 * Find the sum of the digits in the number 100!
 *
 * @author micmeyer
 */
public class Problem0020
{

    public static void main(String[] args)
    {
        BigInteger fac = BigInteger.ONE;
        
        for (int f = 2; f <= 100; f++)
        {
            fac = fac.multiply(BigInteger.valueOf(f));
        }
        
        System.out.println(sumUpDigits(fac));
    }
    
    private static long sumUpDigits(BigInteger i)
    {
        String s = i.toString();
        long sum = 0;
        for (int t = 0; t < s.length(); t++)
        {
            sum += (s.charAt(t) - '0');
        }
        return sum;
    }
}
