package problem0040;

import util.Digits;

/**
 *
 * An irrational decimal fraction is created by concatenating the positive
 * integers:
 * <pre>
 * 0.123456789101112131415161718192021...
 * </pre>
 * It can be seen that the 12th digit of the fractional part is 1.<br/>
 * <br/>
 * If dn represents the nth digit of the fractional part, find the value of the
 * following expression.
 * <pre>
 * d_1 × d_10 × d_100 × d_1000 × d_10000 × d_100000 × d_1000000
 * </pre>
 * 
 * @author micmeyer
 */
public class Problem0040
{

    public static void main(String[] args)
    {
        
        long time = System.currentTimeMillis();
        
        int product = 1;
        int digitCount = 0;
        int number = 0;
        int[] positions = {1, 10, 100, 1000, 10000, 100000, 1000000};
        int index = 0;
        int pos;
        
        while (index < positions.length)
        {
            pos = positions[index];
            
            while (digitCount < pos)
            {
                number++;
                digitCount += Digits.countDigits(number);
            }
            
            product *= getNthDigitFromBack(number, (digitCount - pos));
            
            
            index++;
        }
        
        System.out.println("Product: " + product);
        
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }
    
    private static int getNthDigitFromBack(int number, int n)
    {
        while (n > 0)
        {
            number /= 10;
            n--;
        }
        return number % 10;
    }

}
