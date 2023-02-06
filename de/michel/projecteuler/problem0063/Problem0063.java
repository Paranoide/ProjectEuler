package de.michel.projecteuler.problem0063;

import java.math.BigInteger;

/**
 *
 * The 5-digit number, 16807=7<sup>5</sup>, is also a fifth power. Similarly,
 * the 9-digit
 * number, 134217728=8<sup>9</sup>, is a ninth power.<br/>
 * <br/>
 * How many n-digit positive integers exist which are also an nth power?
 *
 * @author micmeyer
 */
public class Problem0063
{

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        // Special case 1^1 = 1 already included
        int count = 1;
        
        for (int base = 2; base < 10; base++)
        {
            count += findForBase(base);
        }
        
        System.out.println("Result: " + count);
        
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }
    
    public static int findForBase(int base)
    {
        int count = 0;
        int exp = 1;
        BigInteger bigBase = BigInteger.valueOf(base);
        int len = bigBase.toString().length();
        
        while (len == exp)
        {
            count++;
            exp++;
            len = bigBase.pow(exp).toString().length();
        }
        
        return count;
    }
    
    public static int digitCount(int n)
    {
        int count = 0;
        while (n > 0)
        {
            count++;
            n = n / 10;
        }
        return count;
    }

}
