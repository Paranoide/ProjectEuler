package problem0025;

import java.math.BigInteger;

/**
 *
 * The Fibonacci sequence is defined by the recurrence relation:
 * <pre>
 * Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1. Hence the first 12 terms will be:
 * </pre>
 * F1 = 1<br/>
 * F2 = 1<br/>
 * F3 = 2<br/>
 * F4 = 3<br/>
 * F5 = 5<br/>
 * F6 = 8<br/>
 * F7 = 13<br/>
 * F8 = 21<br/>
 * F9 = 34<br/>
 * F10 = 55<br/>
 * F11 = 89<br/>
 * F12 = 144<br/>
 * <br/>
 * The 12th term, F12, is the first term to contain three
 * digits.
 * <br/>
 * What is the index of the first term in the Fibonacci sequence to contain 1000
 * digits?
 *
 * @author Paranoide
 */
public class Problem0025
{
    public static void main(String[] args)
    {
        BigInteger fib1 = BigInteger.ONE;
        BigInteger fib2 = BigInteger.ONE;
        BigInteger end  = BigInteger.TEN.pow(999);
        
        BigInteger curr = fib1.add(fib2);
        int counter = 3;
        
        while (curr.compareTo(end) < 0)
        {
            fib1 = fib2;
            fib2 = curr;
            curr = fib1.add(fib2);
            counter++;
        }
        
        System.out.println(counter);
        
        
    }
}
