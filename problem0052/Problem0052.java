package problem0052;

import util.Digits;

/**
 *
 * It can be seen that the number, 125874, and its double, 251748, contain
 * exactly the same digits, but in a different order.<br/>
 * <br/>
 * Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x,
 * contain the same digits.
 *
 * @author micmeyer
 */
public class Problem0052
{

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        boolean found = false;
        long n = 0;

        while (!found)
        {
            n++;
            found = haveAllSameDigits(n, 2 * n, 3 * n, 4 * n, 5 * n, 6 * n);
        }

        System.out.println("Result: " + n);

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static boolean haveAllSameDigits(long... numbers)
    {
        boolean haveAll = true;

        for (int t = 0; t < numbers.length - 1 && haveAll; t++)
        {
            haveAll = haveSameDigits(numbers[t], numbers[t + 1]);
        }

        return haveAll;
    }

    private static boolean haveSameDigits(long a, long b)
    {
        int[] aDigits = Digits.getDigits(a);
        int[] bDigits = Digits.getDigits(b);

        if (aDigits.length != bDigits.length)
        {
            return false;
        }

        boolean haveSame = true;
        int digit;
        for (int aIndex = 0; aIndex < aDigits.length && haveSame; aIndex++)
        {
            digit = aDigits[aIndex];
            boolean found = false;
            for (int bIndex = 0; bIndex < bDigits.length && !found; bIndex++)
            {
                if (bDigits[bIndex] == digit)
                {
                    bDigits[bIndex] = -1;
                    found = true;
                }
            }
            if (!found)
            {
                haveSame = false;
            }
        }
        return haveSame;
    }

}
