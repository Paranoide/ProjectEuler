package de.michel.projecteuler.util;

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
    
    public static long arrayToLong(int[] a)
    {
        long n = 0;
        int t;
        for (t = 0; t < a.length - 1; t++)
        {
            n += a[t];
            n *= 10;
        }
        return (n + a[t]);
    }
    
    public static long digitListToLong(List<Integer> digits)
    {
        long n = 0;
        for (int i: digits)
        {
            n += i;
            n *= 10;
        }
        return (n / 10);
    }
    
    
    public static int[] getDigits(long n)
    {
        List<Integer> digits = new ArrayList<>();

        while (n > 0)
        {
            digits.add((int)(n % 10));
            n /= 10;
        }
        Collections.reverse(digits);

        return listToArray(digits);
    }
    
    public static List<Integer> getDigitsAsList(long n)
    {
        List<Integer> digits = new ArrayList<>();

        while (n > 0)
        {
            digits.add((int)(n % 10));
            n /= 10;
        }
        Collections.reverse(digits);

        return digits;
    }
    
    
    public static int countDigits(long n)
    {
        int count = (n == 0) ? 1 : 0;
        while (n > 0)
        {
            count++;
            n /= 10;
        }
        return count;
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
