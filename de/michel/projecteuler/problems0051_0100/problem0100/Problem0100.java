package de.michel.projecteuler.problems0051_0100.problem0100;

import java.math.BigInteger;

/**
 * If a box contains twenty-one coloured discs, composed of fifteen blue discs and six red discs, and two discs were taken at random, it can be seen that the
 * probability of taking two blue discs, {@code P(BB) = (15/21)*(14/20) = 1/2}.
 * <br>
 * <br>
 * The next such arrangement, for which there is exactly 50 % chance of taking two blue discs at random, is a box containing eighty-five blue discs and
 * thirty-five red discs.
 * <br>
 * <br>
 * By finding the first arrangement to contain over 10<sup>12</sup>=1.000.000.000.000 discs in total, determine the number of blue discs that the box would
 * contain.
 */
public class Problem0100
{
    private static final BigInteger TARGET = BigInteger.valueOf(1_000_000_000_000L);

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        String result = executeProblem();

        System.out.println();
        System.out.println("Result: " + result);
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    /**
     * This one I solved by examining the first couple of n's and b's for which the condition in question holds (got them by brute forcing).
     * Then I noticed that from one n (or b) to the next it's always almost the same factor (around 5.828 or about sqrt(33.97)) so I assumed I could
     * always get the next ones by just multiplying with that factor. And it worked.
     * <br>
     * What I did exactly is that I multiply the last n by the ratio of the last two found n's and then brute force "around" that calculated number (because
     * it's not EXACTLY that factor). This always got me the next n until it was greater than 10^12. (for the b's holds the same).
     * <br>
     * So... yeah, not exactly solved by algorithm but more through some kind of data analysis and guessing.
     */
    public static String executeProblem()
    {
        Discs discs = new Discs();

        BigInteger n;
        BigInteger b;

        do
        {
            boolean success = discs.calcNext();
            if (!success)
                throw new RuntimeException("Oh oh");

            n = discs.getLastN();
            b = discs.getLastB();
        }
        while (n.compareTo(TARGET) <= 0);

        return b.toString();
    }

    private static double calcProb(long n, long b)
    {
        return (b / (double) n) * ((b - 1) / (double) (n - 1));
    }
}
