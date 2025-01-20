package de.michel.projecteuler.problems0001_0050.problem0016;

import java.math.BigInteger;

/**
 *
 * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.<br/>
 * What is the sum of the digits of the number 2^1000?
 * 
 * @author micmeyer
 */
public class Problem0016
{
    
    public static void main(String[] args)
    {
        BigInteger i = BigInteger.valueOf(2);
        BigInteger result = i.pow(1000);
        
        String resultString = result.toString();
        
        long sum = 0;
        for (int t = 0; t < resultString.length(); t++)
        {
            sum += (resultString.charAt(t) - '0');
        }
        System.out.println(sum);
    }
    
}
