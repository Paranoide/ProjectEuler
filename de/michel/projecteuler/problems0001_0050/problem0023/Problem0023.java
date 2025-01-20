package de.michel.projecteuler.problems0001_0050.problem0023;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import de.michel.projecteuler.util.Divisors;

/**
 *
 * A perfect number is a number for which the sum of its proper divisors is
 * exactly equal to the number. For example, the sum of the proper divisors of
 * 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.
 * <br/><br/>
 * A number n is called deficient if the sum of its proper divisors is less than
 * n and it is called abundant if this sum exceeds n.<br/>
 * <br/>
 * As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest
 * number that can be written as the sum of two abundant numbers is 24. By
 * mathematical analysis, it can be shown that all integers greater than 28123
 * can be written as the sum of two abundant numbers. However, this upper limit
 * cannot be reduced any further by analysis even though it is known that the
 * greatest number that cannot be expressed as the sum of two abundant numbers
 * is less than this limit.<br/>
 * <br/>
 * Find the sum of all the positive integers which cannot be written as the sum
 * of two abundant numbers.
 *
 * @author micmeyer
 */
public class Problem0023
{

    private static final List<Integer> abundants = new ArrayList<>();

    public static boolean isAbundant(int n)
    {
        return Divisors.getAllProperDivisors(n).stream().mapToLong(Long::longValue).sum() > n;
    }

    public static boolean isSumOfTwoAbundants(int n)
    {
        boolean isSum = false;
        int summand = 0;
        for (int t = 0; t < abundants.size() && summand < n && !isSum; t++)
        {
            summand = abundants.get(t);
            int diff = n - summand;
            if (abundantsContains(diff))
            {
                isSum = true;
            }
        }
//        System.out.printf("%5d : %b\n", n, isSum);
        return isSum;
    }

    private static boolean abundantsContains(int n)
    {
        return Collections.binarySearch(abundants, n) >= 0;
    }

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        IntStream.range(1, 28123).filter(Problem0023::isAbundant).forEach(abundants::add);

        long sum = 0;
        for (int t = 1; t < 28124; t++)
        {
            if (!isSumOfTwoAbundants(t))
            {
                sum += t;
            }
        }

        System.out.println(sum);

        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }
}
