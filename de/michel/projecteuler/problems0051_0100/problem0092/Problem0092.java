package de.michel.projecteuler.problems0051_0100.problem0092;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * A number chain is created by continuously adding the square of the digits in a number to form a new number until it has been seen before.
 * <p>
 * For example,
 * <p>
 * 44 → 32 → 13 → 10 → 1 → 1
 * 85 → 89 → 145 → 42 → 20 → 4 → 16 → 37 → 58 → 89
 * <p>
 * Therefore any chain that arrives at 1 or 89 will become stuck in an endless loop. What is most amazing is that EVERY starting number will eventually arrive
 * at 1 or 89.
 * <p>
 * How many starting numbers below ten million will arrive at 89?
 *
 * @author micmeyer
 */
public class Problem0092
{
    private static final int TARGET = 10000000;

    private static final Map<Integer, Integer> ENDING_NUMBERS = new HashMap<>();

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        Integer endingSum;
        int squareSum, result = 0;

        for (int t = 1; t < TARGET; t++)
        {
            squareSum = calcDigitSquareSum(t);

            endingSum = ENDING_NUMBERS.get(squareSum);
            if (endingSum == null)
            {
                endingSum = squareSum;
                while (!endingSum.equals(1) && !endingSum.equals(89))
                    endingSum = calcDigitSquareSum(endingSum);
                ENDING_NUMBERS.put(squareSum, endingSum);
            }

            if (endingSum == 89)
                result++;
        }

        System.out.println("Result: " + result);

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static int calcDigitSquareSum(int n)
    {
        int sum = 0, digit;
        while (n > 0)
        {
            digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }
}
