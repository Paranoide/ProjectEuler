package de.michel.projecteuler.problems0051_0100.problem0053;

import java.math.BigInteger;

/**
 *
 * There are exactly ten ways of selecting three from five, 12345:
 * <pre>
 * 123, 124, 125, 134, 135, 145, 234, 235, 245, and 345
 * </pre>
 * In combinatorics, we use the notation, 5C3 = 10.<br/>
 * <br/>
 * In general,
 * <pre>
 * nCr = n! / ( r!(n−r)! )
 * where r ≤ n, n! = n×(n−1)×...×3×2×1, and 0! = 1.
 * </pre>
 * It is not until n = 23, that a value exceeds one-million: 23C10 =
 * 1144066.<br/>
 * <br/>
 * How many, not necessarily distinct, values of nCr, for 1 ≤ n ≤ 100, are
 * greater than one-million?
 *
 * @author micmeyer
 */
public class Problem0053
{
    
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private static final BigInteger MILLION = BigInteger.valueOf(1000000);

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();
        
        BigInteger result;
        int counter = 0;
        for (int n = 23; n <= 100; n++)
        {
            for (int k = 0; k <= n; k++)
            {
                result = nChooseK(n, k);
                if (result.compareTo(MILLION) > 0)
                {
                    counter++;
                }
            }
        }
        
        System.out.println("Result: " + counter);

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }
    
    private static BigInteger nChooseK(int n, int k)
    {
        if (n < 2*k)
        {
            return nChooseK(n, n-k);
        }
        
        BigInteger numerator, denominator;
        numerator = denominator = BigInteger.ONE;
        int maxFactors = k;
        BigInteger gcd;
        
        for (int factor = 0; factor < maxFactors; factor++)
        {
            numerator = numerator.multiply(BigInteger.valueOf(n));
            denominator = denominator.multiply(BigInteger.valueOf(k));
            
            gcd = gcd(numerator, denominator);
            
            numerator = numerator.divide(gcd);
            denominator = denominator.divide(gcd);
            
            n--;
            k--;
        }
        
        return numerator.divide(denominator);
    }
    
    private static BigInteger nChooseK2(int n, int k)
    {
        BigInteger bigN  = BigInteger.valueOf(n);
        BigInteger bigK  = BigInteger.valueOf(k);
        BigInteger bigNK = bigN.subtract(bigK);
        return factorial(bigN).divide(factorial(bigK).multiply(factorial(bigNK)));
    }
    
    private static BigInteger gcd(BigInteger a, BigInteger b)
    {
        return (b.equals(BigInteger.ZERO)) ? a.abs() : gcd(b, a.mod(b)).abs();
    }
    
    private static BigInteger factorial(BigInteger n)
    {
        if (n.compareTo(TWO) < 0)
        {
            return BigInteger.ONE;
        }
        else
        {
            return n.multiply(factorial(n.subtract(BigInteger.ONE)));
        }
    }

}
