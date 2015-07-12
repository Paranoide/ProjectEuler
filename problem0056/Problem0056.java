package problem0056;

import java.math.BigInteger;

/**
 *
 * A googol (10<sup>100</sup>) is a massive number: one followed by one-hundred zeros;
 * 100<sup>100</sup> is almost unimaginably large: one followed by two-hundred zeros.
 * Despite their size, the sum of the digits in each number is only 1.<br/>
 * <br/>
 * Considering natural numbers of the form, ab, where a, b &lt; 100, what is the
 * maximum digital sum?
 *
 * @author micmeyer
 */
public class Problem0056
{
    
    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();
        
        BigInteger c;
        int sum, maxSum = 0;
        for (int a = 0; a < 100; a++)
        {
            for (int b = 0; b < 100; b++)
            {
                c = BigInteger.valueOf(a).pow(b);
                sum = sumUpDigits(c);
                if (sum > maxSum)
                {
                    maxSum = sum;
                }
            }
        }
        
        System.out.println("Max: " + maxSum);
        
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }
    
    private static int sumUpDigits(BigInteger i)
    {
        int sum = 0;
        
        while (!i.equals(BigInteger.ZERO))
        {
            sum += i.mod(BigInteger.TEN).longValue();
            i = i.divide(BigInteger.TEN);
        }
        return sum;
    }
    
}
