package problem0023;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import util.Divisors;

/**
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
        int item = 0;
        for (int t = 0; t < abundants.size() && item < n && !isSum; t++)
        {
            item = abundants.get(t);
            int diff = n - item;
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
        for (int t = 1; t < 28130; t++)
        {
            if (!isSumOfTwoAbundants(t))
            {
                sum += t;
            }
            if (t % 100 == 0)
            {
                time = System.currentTimeMillis();
            }
        }

        System.out.println(sum);

        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }
}
