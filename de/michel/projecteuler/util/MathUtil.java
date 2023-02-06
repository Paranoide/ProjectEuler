package de.michel.projecteuler.util;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
 * @author Michel Meyer <micmeyer@uos.de>
 */
public class MathUtil
{
    private static final MathContext MATH_CONTEXT = new MathContext(25);

    private static final BigDecimal TWO = new BigDecimal(2L, MATH_CONTEXT);
    private static final BigDecimal FOUR = new BigDecimal(4L, MATH_CONTEXT);

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
//            System.out.println(x);
            // x = (x + n / x) / 2;
            nOverX = n.divide(x, MATH_CONTEXT);

            numeratorSum = x.add(nOverX, MATH_CONTEXT);

            x = numeratorSum.divide(TWO, MATH_CONTEXT);
        }
        while (!lastX.equals(x));

        return x;
    }

    public static boolean isInteger(BigDecimal d)
    {
        char[] digitChars = d.toString().toCharArray();

        boolean nonZeroFound = false;

        for (int t = digitChars.length - 1; t >= 0; t--)
            if (digitChars[t] == '.')
                return !nonZeroFound;
            else if (!nonZeroFound && digitChars[t] != '0')
                return nonZeroFound = true;

        return true;
    }

    public static boolean isSquareNumber(BigDecimal n)
    {
        return isInteger(babylonianSqrt(n));
    }

    public static boolean isSquareNumber2(BigDecimal n)
    {
        if (n.equals(BigDecimal.ONE))
            return true;

        /*
         * Arbitrary initial value for x.
         * n should be fine.
         */
        BigDecimal x = n;

        BigDecimal nOverX, numeratorSum;

        boolean done = false;
        byte pointIndexIfDiffIsAfterPoint = -1;
        byte diffIndex = -1;

        char[] lastDigits = null;
        char[] currDigits = x.toString().toCharArray();

        do
        {
            // x = (x + n / x) / 2;
            x = x.add(n.divide(x, MATH_CONTEXT), MATH_CONTEXT).divide(TWO, MATH_CONTEXT);

            lastDigits = currDigits;
            currDigits = x.toString().toCharArray();

            if (pointIndexIfDiffIsAfterPoint == -1)
            {
                byte index = getPointIndexIfDiffIsAfterPoint(lastDigits, currDigits);
                if (index > -1)
                    pointIndexIfDiffIsAfterPoint = index;
            }

            if (pointIndexIfDiffIsAfterPoint > -1)
            {
                diffIndex = getDiffIndexAfterPoint(lastDigits, currDigits, pointIndexIfDiffIsAfterPoint);

                /*
                 * We have no change BEFORE the decimal point (as
                 * pointIndexIfDiffIsAfterPoint > -1) but also no
                 * change AFTER the point (as diffIndex < 0) we
                 * have no change at all and we are done.
                 */
                if (diffIndex < 0)
                    done = true;
            }

            /*
             * If the digit right before the diff index is not '0'
             * we can assume that it will never get to '0' anymore
             * and so it is NOT an integer.
             */
            if (diffIndex > pointIndexIfDiffIsAfterPoint + 1 && !allZerosFromIndexToIndex(currDigits, (byte) (pointIndexIfDiffIsAfterPoint + 1), diffIndex))
                return false;
        }
        while (!done);

        /*
         * If we reached here the algorithm does not change
         * the digits anymore. Now we only have to check if
         * all of the digits after the decimal point are
         * zero (integer) or not (not integer).
         */
        return allZerosFromIndexToIndex(currDigits, (byte) (pointIndexIfDiffIsAfterPoint + 1), (byte) currDigits.length);
    }

    private static boolean allZerosFromIndexToIndex(char[] digits, byte startIndex, byte endIndex)
    {
        for (byte t = startIndex; t < endIndex; t++)
            if (digits[t] != '0')
                return false;

        return true;
    }

    private static byte getPointIndexIfDiffIsAfterPoint(char[] digits1, char[] digits2)
    {
        for (byte t = 0; t < digits1.length; t++)
            if (digits1[t] != digits2[t])
                return -1;
            else if (digits1[t] == '.' && digits2[t] == '.')
                return t;

        return -1;
    }

    private static byte getDiffIndexAfterPoint(char[] digits1, char[] digits2, byte pointIndex)
    {
        if (digits1.length > digits2.length)
            return getDiffIndexAfterPoint(digits2, digits1, pointIndex);

        /*
         * The length of digits1 will always be lower or equal
         * to the length of digits2.
         */
        for (byte t = (byte) (pointIndex + 1); t < digits1.length; t++)
            if (digits1[t] != digits2[t])
                return t;

        return -1;
    }

    /**
     * index >= 0: index of the first non zero digit
     * index == -1: no non zero digit
     * index == -2: no decimal point
     *
     * @param d
     *
     * @return
     */
    private static int[] firstNonZeroDecimalDigitAndIndex(BigDecimal d)
    {
        char[] digits = d.toString().toCharArray();
        int[] result = new int[2];

        boolean pointFound = false;

        int resultIndex = -1;

        for (int index = 0; index < digits.length && resultIndex < 0; index++)
            if (digits[index] == '.')
                pointFound = true;
            else if (pointFound && digits[index] != '0')
                resultIndex = index;

        if (resultIndex >= 0)
        {
            result[0] = digits[resultIndex];
            result[1] = resultIndex;
        }
        else
        {
            result[0] = -1;
            result[1] = pointFound ? -1 : -2;
        }

        return result;
    }
}
