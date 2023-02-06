package de.michel.projecteuler.problem0030;

/**
 *
 * Surprisingly there are only three numbers that can be written as the sum of
 * fourth powers of their digits:
 * <pre>
 * 1634 = 1^4 + 6^4 + 3^4 + 4^4
 * 8208 = 8^4 + 2^4 + 0^4 + 8^4
 * 9474 = 9^4 + 4^4 + 7^4 + 4^4
 * </pre>
 * As 1 = 1^4 is not a sum it is not included.<br/>
 * <br/>
 * The sum of these numbers is 1634 + 8208 + 9474 = 19316.<br/>
 * <br/>
 * Find the sum of all the numbers that can be written as the sum of fifth
 * powers of their digits.
 *
 * @author Paranoide
 */
public class Problem0030
{

    public static void main(String[] args)
    {
        long sum = 0;
        for (int t = 2; t < 1000000; t++)
        {
            if (isSumOfAPowerOfDigits(t, 5))
            {
                sum += t;
            }
        }
        
        System.out.println(sum);
        
    }
    
    
    private static boolean isSumOfAPowerOfDigits(int n, int power)
    {
        int number = n;
        int sum = 0;
        boolean isSum = true;
        int len = String.valueOf(n).length() - 1;
        int digit;
        
        while (isSum && len >= 0)
        {
            int powerToTen = ((int)Math.pow(10, len));
            
            digit = n / powerToTen;
            
            sum += (int)Math.pow(digit, power);
            if (sum > number)
            {
                isSum = false;
            }
            
            n -= digit*powerToTen;
            len--;
        }
        
        isSum = (sum == number) && isSum;
        
        return isSum;
        
    }
}
