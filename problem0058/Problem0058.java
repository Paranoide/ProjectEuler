package problem0058;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import util.PrimeGenerator;

/**
 *
 * Starting with 1 and spiralling anticlockwise in the following way, a square
 * spiral with side length 7 is formed.
 * <pre>
 * 37 36 35 34 33 32 31
 * 38 17 16 15 14 13 30
 * 39 18  5  4  3 12 29
 * 40 19  6  1  2 11 28
 * 41 20  7  8  9 10 27
 * 42 21 22 23 24 25 26
 * 43 44 45 46 47 48 49
 * </pre>
 * It is interesting to note that the odd squares lie along the bottom right
 * diagonal, but what is more interesting is that 8 out of the 13 numbers lying
 * along both diagonals are prime; that is, a ratio of 8/13 ≈ 62%.<br/>
 * <br/>
 * If one complete new layer is wrapped around the spiral above, a square spiral
 * with side length 9 will be formed. If this process is continued, what is the
 * side length of the square spiral for which the ratio of primes along both
 * diagonals first falls below 10%?
 *
 * @author micmeyer
 */
public class Problem0058
{

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        double perc = 1.0;
//        int[] corners = new int[4];
        int corner;
        int n = 0;
        int primeCount = 0;
        int totalCount = 1;

        while (perc >= 0.1)
        {
            for (int c = 0; c < 4; c++)
            {
                corner = 4 * (n * n + n) + 2 * (c + 1) * (n + 1) + 1;

                if (PrimeGenerator.rudimentalIsPrime(corner))
                {
                    primeCount++;
                }
            }

            totalCount += 4;
            n++;

            perc = 1.0 * primeCount / totalCount;
        }

        System.out.println("Side length: " + (2 * n + 1));

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

}
