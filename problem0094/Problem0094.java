package problem0094;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
 *
 *
 * @author micmeyer
 */
public class Problem0094
{
    private static final long TARGET = 100000L; //1000000000L;

    private static final MathContext MATH_CONTEXT = new MathContext(15);

    private static final BigDecimal TWO = new BigDecimal(2L, MATH_CONTEXT);
    private static final BigDecimal FOUR = new BigDecimal(4L, MATH_CONTEXT);

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

//        for (long a = 0; a < 10000000000L; a++)
//        {
//
//        }
        long result = 0;

        for (long a = 2; a <= TARGET / 3; a++)
        {
            if (hasIntegerArea(a, a - 1))
            {
                result += 3 * a - 1;
//                long c = a - 1;
//                System.out.println(MessageFormat.format("{0}, {1}, {2} => {3}", a, a, c, c / 2.0 * Math.sqrt(a * a - c * c / 4.0)));
            }
            if (hasIntegerArea(a, a + 1))
            {
                result += 3 * a + 1;
//                long c = a + 1;
//                System.out.println(MessageFormat.format("{0}, {1}, {2} => {3}", a, a, c, c / 2.0 * Math.sqrt(a * a - c * c / 4.0)));
            }
        }

        System.out.println("Result: " + result);
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static boolean hasIntegerArea(long a, long c)
    {
        BigDecimal area = calcArea(a, c);

        return isInteger(area);
    }

//    private static BigDecimal calcArea2(long a, long c)
//    {
//        BigDecimal bigA = new BigDecimal(a, MATH_CONTEXT);
//        BigDecimal bigC = new BigDecimal(c, MATH_CONTEXT);
//
//        BigDecimal s = bigA.multiply(TWO, MATH_CONTEXT).add(bigC).divide(TWO, MATH_CONTEXT);
//
//        BigDecimal a2 = s.subtract(bigA, MATH_CONTEXT);
//        BigDecimal c2 = s.subtract(bigC, MATH_CONTEXT);
//
//        BigDecimal product = s.multiply(a2, MATH_CONTEXT).multiply(a2, MATH_CONTEXT).multiply(c2, MATH_CONTEXT);
//
//        return babylonianSqrt(product);
//    }
    private static BigDecimal calcArea(long a, long c)
    {
        BigDecimal bigA = new BigDecimal(a, MATH_CONTEXT);
        BigDecimal bigC = new BigDecimal(c, MATH_CONTEXT);

        BigDecimal bigASquared = bigA.multiply(bigA, MATH_CONTEXT);
        BigDecimal bigCSquared = bigC.multiply(bigC, MATH_CONTEXT);

        BigDecimal bigCSquaredOver4 = bigCSquared.divide(FOUR, MATH_CONTEXT);

        BigDecimal innerSqrt = bigASquared.subtract(bigCSquaredOver4, MATH_CONTEXT);

        BigDecimal sqrt = babylonianSqrt(innerSqrt);

        return bigC.divide(TWO, MATH_CONTEXT).multiply(sqrt, MATH_CONTEXT);
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

    private static boolean isInteger(BigDecimal d)
    {
        char[] digitChars = d.toString().toCharArray();

        for (int t = digitChars.length - 1; t >= 0; t--)
            if (digitChars[t] == '.')
                return true;
            else if (digitChars[t] != '0')
                return false;

        return true;
    }
}
