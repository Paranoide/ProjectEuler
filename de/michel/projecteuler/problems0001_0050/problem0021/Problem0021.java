package de.michel.projecteuler.problems0001_0050.problem0021;

import java.util.HashSet;
import java.util.Set;
import de.michel.projecteuler.util.Divisors;

/**
 *
 * Let d(n) be defined as the sum of proper divisors of n (numbers less than n
 * which divide evenly into n).<br/>
 * If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and
 * each of a and b are called amicable numbers.<br/>
 * <br/>
 * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44,
 * 55 and 110; therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4,
 * 71 and 142; so d(284) = 220.<br/>
 * <br/>
 * Evaluate the sum of all the amicable numbers under 10000.
 *
 * @author micmeyer
 */
public class Problem0021
{

    public static void main(String[] args)
    {
        
        Set<Long> amicables = new HashSet<>();
        long result;
        for (long n = 2; n < 10000; n++)
        {
            result = testAmicable(n);
            if (result > 0)
            {
                amicables.add(n);
                amicables.add(result);
            }
        }
        
        System.out.println(amicables.stream().mapToLong(Long::valueOf).sum());
    }

    public static long sumOfDivisors(long n)
    {
        return Divisors.getAllDivisors(n).stream().mapToLong(Long::valueOf).sum();
    }
    
    public static long testAmicable(long n)
    {
        long sum = sumOfDivisors(n) - n;
        if (n == sum)
        {
            return -1;
        }
        return ( (sumOfDivisors(sum) - sum) == n ) ? sum : -1;
    }
}
