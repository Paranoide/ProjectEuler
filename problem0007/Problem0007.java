package problem0007;

import java.util.LinkedList;
import java.util.List;
import util.PrimeGenerator;

/**
 *
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
 * that the 6th prime is 13.<br />
 * What is the 10 001st prime number?
 *
 * @author micmeyer
 */
public class Problem0007
{

    private static List<Long> primes = new LinkedList<>();

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();
        
        PrimeGenerator pg = new PrimeGenerator();
        System.out.println(pg.generateFirstNPrimes(10001).get(10000));
        
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

}
