package de.michel.projecteuler.problems0001_0050.problem0006;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 *
 * The sum of the squares of the first ten natural numbers is,
 * <pre>1^2 + 2^2 + ... + 10^2 = 385</pre> The square of the sum of the first
 * ten natural numbers is,
 * <pre>(1 + 2 + ... + 10)^2 = 552 = 3025</pre> Hence the difference between the
 * sum of the squares of the first ten natural numbers and the square of the sum
 * is 3025 − 385 = 2640.<br />
 * Find the difference between the sum of the squares of the first one hundred
 * natural numbers and the square of the sum.
 *
 * @author micmeyer
 */
public class Problem0006
{

    public static void main(String[] args)
    {
        final int TIMES = 1000000;
        final int N = 100;
        long result;

        long time = System.currentTimeMillis();

        for (int t = 0; t < TIMES; t++)
        {
            result = squareOfSum(N) - sumOfSquares(N);
//            System.out.println(result);
        }

        System.out.println("Time 1: " + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();

        for (int t = 0; t < TIMES; t++)
        {
            result = ((long) Math.pow(LongStream.range(1, N + 1).sum(), 2))
                    - LongStream.range(1, N + 1).map(l -> l * l).sum();
//            System.out.println(result);
        }
        
        
        System.out.println("Time 2: " + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        
        List<Integer> numbers = new ArrayList<>();
        IntStream.range(1, N+1).forEach(i -> numbers.add(i));
        for (int t = 0; t < TIMES; t++)
        {
            result = ((long) Math.pow(numbers.stream().mapToInt(Integer::intValue).sum(), 2) -
                      numbers.stream().mapToInt(Integer::intValue).map(i -> i*i).sum());
        }

        System.out.println("Time 3: " + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();

        for (int t = 0; t < TIMES; t++)
        {
            result = squareOfSumDirect(N) - sumOfSquaresDirect(N);
//            System.out.println(result);
        }

        System.out.println("Time 4: " + (System.currentTimeMillis() - time));
    }

    private static long sumOfSquares(int n)
    {
        long sum = 0;
        for (int t = 0; t < n; t++)
        {
            sum += (t + 1) * (t + 1);
        }
        return sum;
    }

    private static long squareOfSum(int n)
    {
        long sum = 0;
        for (int t = 0; t < n; t++)
        {
            sum += (t + 1);
        }
        return sum * sum;
    }

    private static long sumOfSquaresDirect(int n)
    {
        return n * (n + 1) * (2 * n + 1) / 6;
    }

    private static long squareOfSumDirect(int n)
    {
        return (long) Math.pow(n * (n + 1) / 2, 2);
    }
}
