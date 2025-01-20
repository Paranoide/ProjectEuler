package de.michel.projecteuler.problems0001_0050.problem0036;

/**
 *
 * The decimal number, 585 = 1001001001 (binary), is palindromic in both bases.
 *
 * Find the sum of all numbers, less than one million, which are palindromic in
 * base 10 and base 2.
 *
 * (Please note that the palindromic number, in either base, may not include
 * leading zeros.)
 *
 * @author micmeyer
 */
public class Problem0036
{

    public static void main(String[] args)
    {
        int sum = 0;
        System.out.println(intToBinary(42));
        System.out.println(5 >> 1);
        for (int t = 0; t < 1000000; t++)
        {
            if (isPalindrome(t) && isPalindrome(intToBinary(t)))
            {
                sum += t;
            }
        }
        System.out.println("Sum: " + sum);
    }

    private static String intToBinary(int n)
    {
        String b = "";

        if (n == 0 || n == 1)
        {
            return String.valueOf(n);
        }

        while (n > 0)
        {
            b = ((n % 2 == 0) ? "0" : "1") + b;
            n = (n >> 1);
        }

        return b;
    }

    private static boolean isPalindrome(int n)
    {
        return isPalindrome(String.valueOf(n));
    }

    private static boolean isPalindrome(String s)
    {
        boolean isPal = true;
        int len = s.length();
        for (int t = 0; t < len / 2 && isPal; t++)
        {
            isPal = (s.charAt(t) == s.charAt(len - t - 1));
        }

        return isPal;
    }

}
