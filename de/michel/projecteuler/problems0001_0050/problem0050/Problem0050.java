package de.michel.projecteuler.problems0001_0050.problem0050;

import java.util.Collections;
import java.util.List;
import de.michel.projecteuler.util.PrimeGenerator;

/**
 *
 *
 *
 * @author micmeyer
 */
public class Problem0050
{

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();
        
        alternative();
        
//        List<Long> primes = new PrimeGenerator().generatePrimesSmallerThanN(1000000);
//
//        long maxPrime = 0;
//        int maxCount = 0;
//        for (int currPrimeIndex = primes.size() - 1; currPrimeIndex >= 0; currPrimeIndex--)
//        {
//            long sum = 0;
//            long currPrime = primes.get(currPrimeIndex);
//            
//            int pIndex;
//
//            int currStartIndex = currPrimeIndex - 1;
//            int actualStartIndex = currStartIndex;
//            
//            while (currStartIndex > 0)
//            {
//                for (pIndex = currStartIndex; pIndex >= 0 && sum < currPrime; pIndex--)
//                {
//                    sum += primes.get(pIndex);
//                }
//                
//
//                int count = actualStartIndex - pIndex;
//                
//                if (sum == currPrime && count > maxCount)
//                {
//                    maxPrime = sum;
//                    maxCount = count;
//                }
//                
//                sum -= primes.get(actualStartIndex);
//                currStartIndex = pIndex;
//                
//                actualStartIndex--;
//            }
//        }
//
//        System.out.println("Max prime: " + maxPrime);
//        System.out.println("Max count: " + maxPrime);

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    
    private static void alternative()
    {
        PrimeGenerator pg = new PrimeGenerator();
        List<Long> primes = pg.generatePrimesSmallerThanN(1000000);
        
        long sum = 0;
        int maxCount = 0;
        long maxPrime = 0;
        int count = 0;
        
        while (sum < 1000000)
        {
            sum += pg.next();
            count++;
            if (contains(primes, sum))
            {
                maxCount = count;
                maxPrime = sum;
            }
            else
            {
                long sum2 = sum;
                int until = count - maxCount - 1;
                int count2 = 0;
                while (count2 < until)
                {
                    sum2 -= primes.get(count2);
                    count2++;
                    if (contains(primes, sum2))
                    {
                        maxCount = count - count2;
                        maxPrime = sum;
                        count2 = until;
                    }
                    
                }
            }
        }
        
        System.out.println("Max count: " + maxCount);
        System.out.println("Max prime: " + maxPrime);
        
        
        
    }
    
    private static boolean contains(List<Long> list, Long item)
    {
        return Collections.binarySearch(list, item) >= 0;
    }

}
