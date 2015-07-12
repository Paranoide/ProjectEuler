package problem0057;

import java.math.BigInteger;

/**
 *
 * It is possible to show that the square root of two can be expressed as an
 * infinite continued fraction.
 * <pre>
 * √ 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...
 * </pre>
 * By expanding this for the first four iterations, we get:
 * <pre>
 * 1 + 1/2 = 3/2 = 1.5
 * 1 + 1/(2 + 1/2) = 7/5 = 1.4
 * 1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
 * 1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...
 * </pre>
 * The next three expansions are 99/70, 239/169, and 577/408, but the eighth
 * expansion, 1393/985, is the first example where the number of digits in the
 * numerator exceeds the number of digits in the denominator.<br/>
 * <br/>
 * In the first one-thousand expansions, how many fractions contain a numerator
 * with more digits than denominator?
 *
 * @author micmeyer
 */
public class Problem0057
{

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        int count;

        count = method2();

        System.out.println("Count: " + count);

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static int method1()
    {
        BigInteger one = BigInteger.ONE;
        BigFraction f = new BigFraction(one, BigInteger.valueOf(2));
        f = f.add(one);
        int count = 0;

        for (int t = 0; t < 999; t++)
        {
            f = f.add(one).reciproc().add(one);
            if (f.getNumerator().toString().length() > f.getDenominator().toString().length())
            {
                count++;
            }
        }
        return count;
    }

    private static int method2()
    {
        int count = 0;

        final BigInteger TWO = BigInteger.valueOf(2);
        BigInteger num = BigInteger.valueOf(3);
        BigInteger den = TWO;
        BigInteger tmpNum;
//        BigInteger gcd;

        for (int t = 0; t < 999; t++)
        {
            tmpNum = num;
            num = num.add(den.multiply(TWO));
            den = den.add(tmpNum);

            /*
             * Seems to be obsolete as the gcd is always 1
             * gcd = BigFraction.gcd(num, den);
             * num = num.divide(gcd);
             * den = den.divide(gcd);
             */
            if (num.toString().length() > den.toString().length())
            {
                count++;
            }
        }

        return count;
    }

}
