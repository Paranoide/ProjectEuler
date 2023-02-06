package de.michel.projecteuler.problem0087;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import de.michel.projecteuler.util.PrimeGenerator;

/**
 *
 * The smallest number expressible as the sum of a prime square, prime cube, and prime fourth power is 28. In fact, there are exactly four numbers below fifty
 * that can be expressed in such a way:
 * <pre>
 * 28 = 2<sup>2</sup> + 2<sup>3</sup> + 2<sup>4</sup>
 * 33 = 3<sup>2</sup> + 2<sup>3</sup> + 2<sup>4</sup>
 * 49 = 5<sup>2</sup> + 2<sup>3</sup> + 2<sup>4</sup>
 * 47 = 2<sup>2</sup> + 3<sup>3</sup> + 2<sup>4</sup>
 * </pre>
 * How many numbers below fifty million can be expressed as the sum of a prime square, prime cube, and prime fourth power?
 *
 * @author micmeyer
 */
public class Problem0087
{
    private static final long TARGET_VALUE = 50000000;

    /**
     *
     * Very 'brute forcy' method.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        /*
         * The greatest primes that are possible for x, y and z.
         * Any prime greater than these would make the sum greater
         * than the target.
         */
        long maxX = (long) Math.sqrt(TARGET_VALUE);
        long maxY = (long) Math.pow(TARGET_VALUE, 1.0 / 3.0);
        long maxZ = (long) Math.pow(TARGET_VALUE, 1.0 / 4.0);

        PrimeGenerator pg = new PrimeGenerator();

        /*
         * In these lists there will be all the necessary primes to
         * iterate over. The cross product of these primes are the
         * combinations to look at.
         */
        List<Long> primesX = new ArrayList<>(pg.generatePrimesSmallerThanN(maxX + 1));
        List<Long> primesY = new ArrayList<>(pg.generatePrimesSmallerThanN(maxY + 1));
        List<Long> primesZ = new ArrayList<>(pg.generatePrimesSmallerThanN(maxZ + 1));

        /*
         * Set is important as we only want to know the distinct
         * amount of numbers.
         */
        Set<Long> numbers = new HashSet<>();
        long sum;

        /*
         * The cross product. Brute force. Calculate sums and push
         * them into the set.
         */
        for (long x : primesX)
            for (long y : primesY)
                for (long z : primesZ)
                    if ((sum = calcSum(x, y, z)) < TARGET_VALUE)
                        numbers.add(sum);

        System.out.println("Result: " + numbers.size());

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static long calcSum(long x, long y, long z)
    {
        return (long) (Math.pow(x, 2) + Math.pow(y, 3) + Math.pow(z, 4));
    }
}
