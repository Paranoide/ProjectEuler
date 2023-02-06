package de.michel.projecteuler.problem0004;

/**
 *
 * A palindromic number reads the same both ways. The largest palindrome made
 * from the product of two 2-digit numbers is 9009 = 91 × 99.<br />
 * Find the largest palindrome made from the product of two 3-digit numbers.
 *
 * @author micmeyer
 */
public class Problem0004
{

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        int largestFac1 = -1, largestFac2 = -1;
        int largestProd = -1;
        for (int fac1 = 999; fac1 > 99; fac1--)
        {
            int fac2 = fac1;
            for (; fac2 > 99; fac2--)
            {
                int prod = fac1 * fac2;
                if (prod > largestProd)
                {
                    if (isPalindrome(prod))
                    {
                        largestFac1 = fac1;
                        largestFac2 = fac2;
                        largestProd = prod;
                    }
                }
            }
        }

        System.out.println("Largest palindrome is: " + largestProd);
        System.out.println("fac1 * fac2 = " + largestFac1 + " * " + largestFac2);

        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static boolean isPalindrome(int n)
    {
        return isPalindrome(((Integer) n).toString());
    }

    private static boolean isPalindrome(String s)
    {
        boolean isPal = true;
        int len = s.length();

        for (int index = 0; index < len / 2 && isPal; index++)
        {
            int backIndex = len - index - 1;

            if (s.charAt(index) != s.charAt(backIndex))
            {
                isPal = false;
            }
        }

        return isPal;
    }
}
