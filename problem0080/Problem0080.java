package problem0080;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *
 * It is well known that if the square root of a natural number is not an integer, then it is irrational. The decimal expansion of such square roots is infinite
 * without any repeating pattern at all.
 * <p>
 * The square root of two is 1.41421356237309504880..., and the digital sum of the first one hundred decimal digits is 475.
 * <p>
 * For the first one hundred natural numbers, find the total of the digital sums of the first one hundred decimal digits for all the irrational square roots.
 *
 * @author micmeyer
 */
public class Problem0080
{
    private static final BigDecimal TWO = BigDecimal.valueOf(2.0);

    /**
     * A little bit more than 100 digits precision just to be sure.
     * RoundingMode should be irrelevant.
     */
    private static final MathContext MATH_CONTEXT = new MathContext(103, RoundingMode.HALF_EVEN);

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        BigDecimal bigN, sqrt;

        int sum = 0;
        for (int n = 1; n <= 100; n++)
        {
            /*
             * Exclude all integer square roots
             */
            if (hasIntegerSqrt(n))
                continue;

            bigN = new BigDecimal(n, MATH_CONTEXT);

            sqrt = babylonianSqrt(bigN);

            sum += sumOfFirst100Digits(sqrt);
        }

        System.out.println("Result: " + sum);

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    /**
     * Babylonian proximity square root method.
     *
     * @param n
     *
     * @return
     */
    public static BigDecimal babylonianSqrt(BigDecimal n)
    {
        /*
         * Arbitrary initial value for x.
         * n/2 should be fine.
         */
        BigDecimal x = n.divide(TWO, MATH_CONTEXT);

        BigDecimal nOverX, numeratorSum, lastX;

        /*
         * Do it until x does not change anymore. Then
         * we have enough precision.
         */
        do
        {
            lastX = x;

            // x = (x + n / x) / 2;
            nOverX = n.divide(x, MATH_CONTEXT);

            numeratorSum = x.add(nOverX, MATH_CONTEXT);

            x = numeratorSum.divide(TWO, MATH_CONTEXT);
        }
        while (!lastX.equals(x));

        return x;
    }

    /**
     * Sums up the first 100 digits of a given BigDecimal n
     * regardless if the digits are "before" of "after" the
     * decimal point.
     *
     * @param n
     *
     * @return
     */
    private static int sumOfFirst100Digits(BigDecimal n)
    {
        char[] sqrtDigits = n.toString().toCharArray();

        int sum = 0, index = 0, count = 0;

        while (index < sqrtDigits.length && count < 100)
        {
            int digit = sqrtDigits[index++];

            if (digit != '.')
            {
                sum += (digit - '0');
                count++;
            }
        }

        return sum;
    }

    /**
     * Is sqrt(n) an integer?
     *
     * @param n
     *
     * @return
     */
    private static boolean hasIntegerSqrt(int n)
    {
        double sqrt = Math.sqrt(n);

        return ((int) sqrt) == sqrt;
    }
}
