package de.michel.projecteuler.problems0051_0100.problem0055;

import java.math.BigInteger;

/**
 *
 * If we take 47, reverse and add, 47 + 74 = 121, which is palindromic.
 *
 * Not all numbers produce palindromes so quickly. For example,
 * <pre>
 * 349 + 943 = 1292,
 * 1292 + 2921 = 4213
 * 4213 + 3124 = 7337
 * </pre>
 * That is, 349 took three iterations to arrive at a palindrome.<br/>
 * <br/>
 * Although no one has proved it yet, it is thought that some numbers, like 196,
 * never produce a palindrome. A number that never forms a palindrome through
 * the reverse and add process is called a Lychrel number. Due to the
 * theoretical nature of these numbers, and for the purpose of this problem, we
 * shall assume that a number is Lychrel until proven otherwise. In addition you
 * are given that for every number below ten-thousand, it will either (i) become
 * a palindrome in less than fifty iterations, or, (ii) no one, with all the
 * computing power that exists, has managed so far to map it to a palindrome. In
 * fact, 10677 is the first number to be shown to require over fifty iterations
 * before producing a palindrome: 4668731596684224866951378664 (53 iterations,
 * 28-digits).<br/>
 * <br/>
 * Surprisingly, there are palindromic numbers that are themselves Lychrel
 * numbers; the first example is 4994.<br/>
 * <br/>
 * How many Lychrel numbers are there below ten-thousand?
 *
 * NOTE: Wording was modified slightly on 24 April 2007 to emphasise the
 * theoretical nature of Lychrel numbers.
 *
 * @author micmeyer
 */
public class Problem0055
{

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        int count = 0;
        BigInteger i;
        boolean lychrel;
        int iteration;
        for (int t = 0; t < 10000; t++)
        {
            i = BigInteger.valueOf(t);
            lychrel = true;
            iteration = 0;
            while (iteration < 50 && lychrel)
            {
                i = i.add(reverse(i));
                if (isPalindrome(i))
                {
                    lychrel = false;
                }
                iteration++;
            }
            
            if (lychrel)
            {
                count++;
            }
        }
        
        System.out.println("Count: " + count);
        
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }
    
    private static BigInteger reverse(BigInteger i)
    {
        String str = i.toString();
        String revStr = "";
        for (int t = str.length() - 1; t >= 0; t--)
        {
            revStr += str.charAt(t);
        }
        return new BigInteger(revStr);
    }
    
    private static boolean isPalindrome(BigInteger i)
    {
        String str = i.toString();
        int len = str.length();
        for (int t = 0; t < len / 2; t++)
        {
            if (str.charAt(t) != str.charAt(len - t - 1))
            {
                return false;
            }
        }
        return true;
    }

}
