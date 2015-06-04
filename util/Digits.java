package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author micmeyer
 */
public class Digits
{
    public static int arrayToInt(int[] a)
    {
        int n = 0;
        int t;
        for (t = 0; t < a.length - 1; t++)
        {
            n += a[t];
            n *= 10;
        }
        return (n + a[t]);
    }
    
    
    public static int[] getDigits(int n)
    {
        List<Integer> digits = new ArrayList<>();

        while (n > 0)
        {
            digits.add(n % 10);
            n /= 10;
        }
        Collections.reverse(digits);

        return listToArray(digits);
    }
    
    public static List<Integer> getDigitsAsList(int n)
    {
        List<Integer> digits = new ArrayList<>();

        while (n > 0)
        {
            digits.add(n % 10);
            n /= 10;
        }
        Collections.reverse(digits);

        return digits;
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
}
