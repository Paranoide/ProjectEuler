
package problem0001;

import java.util.stream.IntStream;

/**
 *
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we
 * get 3, 5, 6 and 9. The sum of these multiples is 23.<br />
 * Find the sum of all the multiples of 3 or 5 below 1000.
 *
 * @author micmeyer
 */
public class Problem0001
{
    public static void main(String[] args)
    {
        int sum = IntStream.range(1, 1000).filter(x -> x % 3 == 0 || x % 5 == 0).sum();
        System.out.println(sum);
    }
}
