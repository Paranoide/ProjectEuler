package problem0050;

import java.util.Collections;
import java.util.List;
import util.PrimeGenerator;

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

        List<Long> primes = new PrimeGenerator().generatePrimesSmallerThanN(1000000);

        long maxPrime = 0;
        int maxCount = 0;
        for (int currPrimeIndex = primes.size() - 1; currPrimeIndex >= 0; currPrimeIndex--)
        {
            long sum = 0;
            long currPrime = primes.get(currPrimeIndex);
            
            int pIndex;

            int currStartIndex = currPrimeIndex - 1;
            int actualStartIndex = currStartIndex;
            
            while (currStartIndex > 0)
            {
                for (pIndex = currStartIndex; pIndex >= 0 && sum < currPrime; pIndex--)
                {
                    sum += primes.get(pIndex);
                }
                

                int count = actualStartIndex - pIndex;
                
                if (sum == currPrime && count > maxCount)
                {
                    maxPrime = sum;
                    maxCount = count;
                }
                
                sum -= primes.get(actualStartIndex);
                currStartIndex = pIndex;
                
                actualStartIndex--;
            }
        }

        System.out.println("Max: " + maxPrime);

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static boolean contains(List<Long> list, Long item)
    {
        return Collections.binarySearch(list, item) >= 0;
    }

}
