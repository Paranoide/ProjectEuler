package problem0033;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static util.Digits.*;

/**
 *
 * The fraction 49/98 is a curious fraction, as an inexperienced mathematician
 * in attempting to simplify it may incorrectly believe that 49/98 = 4/8, which
 * is correct, is obtained by cancelling the 9s.
 *
 * We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
 *
 * There are exactly four non-trivial examples of this type of fraction, less
 * than one in value, and containing two digits in the numerator and
 * denominator.
 *
 * If the product of these four fractions is given in its lowest common terms,
 * find the value of the denominator.
 *
 * @author micmeyer
 */
public class Problem0033
{

    public static void main(String[] args)
    {
        
        List<Fraction> curious = new ArrayList<>();
        
        for (int a = 10; a < 99; a++)
        {
            for (int b = a + 1; b < 100; b++)
            {
                int[] commons = getCommonDigits(a, b);

                if (commons.length > 0)
                {
                    if (commons.length > 1 || commons[0] != 0)
                    {
                        for (int c : commons)
                        {
                            Fraction f1 = new Fraction(a, b);

                            int a2 = removeDigit(a, c);
                            int b2 = removeDigit(b, c);

                            if (b2 != 0)
                            {
                                Fraction f2 = new Fraction(a2, b2);
                                if (f1.equals(f2))
                                {
                                    curious.add(f1);
                                }
                            }
                        }
                    }
                }
            }
        }
        
        curious.stream().reduce((a, b) -> a.multiply(b)).ifPresent(f -> System.out.println(f.getDenominator()));

    }

    private static int[] getCommonDigits(int a, int b)
    {
        int[] aDigits = getDigits(a);
        int[] bDigits = getDigits(b);
        int[] commonDigits = getIntersection(aDigits, bDigits);

        return commonDigits;
    }

    private static int[] listToArray(List<Integer> list)
    {
        int[] a = new int[list.size()];
        for (int t = 0; t < a.length; t++)
        {
            a[t] = list.get(t);
        }
        return a;
    }


    private static int[] getIntersection(int[] set1, int[] set2)
    {
        List<Integer> ints = new ArrayList<>();
        for (int a : set1)
        {
            for (int b : set2)
            {
                if (a == b)
                {
                    if (!ints.contains(a))
                    {
                        ints.add(a);
                    }
                }
            }
        }
        return listToArray(ints);
    }

    private static int removeDigit(int n, int digit)
    {
        int[] digits = getDigits(n);
        int index = -1;
        int len = digits.length;
        for (int t = 0; t < len && index < 0; t++)
        {
            if (digits[t] == digit)
            {
                index = t;
            }
        }
        int[] newDigits = new int[len - 1];

        System.arraycopy(digits, 0, newDigits, 0, index);
        System.arraycopy(digits, index + 1, newDigits, index, len - index - 1);

        return arrayToInt(newDigits);
    }

}
