package de.michel.projecteuler.problem0005;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * 2520 is the smallest number that can be divided by each of the numbers
 * from 1 to 10 without any remainder.<br />
 * What is the smallest positive number that is evenly divisible by all of 
 * the numbers from 1 to 20?
 * 
 * @author micmeyer
 */
public class Problem0005
{
    public static void main(String[] args)
    {
        List<Integer> factors = new ArrayList<>();
        
        IntStream.range(1, 20).forEach(div ->
        {
            List<Integer> primes = getPrimeFactors(div);
            factors.stream().forEach((item) -> { primes.remove(item); });
            factors.addAll(primes);
        });
        
//        Collections.sort(factors);
//        System.out.println(factors.toString());
        
        IntStream stream = factors.stream().mapToInt(Integer::intValue);
        System.out.println(stream.reduce(1, (prod, item) -> prod*item));
        
        
        System.out.println(ggT(15, 110));
    }
    
    
    
    private static List<Integer> getPrimeFactors(int n)
    {
        List<Integer> primes = new ArrayList<>();
        
        while (n % 2 == 0)
        {
            primes.add(2);
            n /= 2;
        }
        
        int prime = 3;
        while (n > 1)
        {
            if (n % prime == 0)
            {
                n /= prime;
                primes.add(prime);
            }
            else
            {
                prime += 2;
            }
        }
        
        return primes;
    }
    
    private static int ggT(int a, int b)
    {
        if (a < b)
        {
            return ggT(b, a);
        }
        
        if (b == 0)
        {
            return a;
        }
        
        return ggT(b, a % b);
    }
}
