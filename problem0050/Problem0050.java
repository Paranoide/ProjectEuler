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

        System.out.println(primes.size());
        System.out.println((primes.size() * (primes.size() + 1)) / 2);

        long maxPrime = 0;
        int maxCount = 0;
        for (int firstIndex = primes.size() - 2; firstIndex >= 0; firstIndex--)
        {
            long sum = 0;
            for (int lastIndex = firstIndex - 1; lastIndex >= 0 && sum < 1000000; lastIndex--)
            {
                for (int pIndex = firstIndex; pIndex >= lastIndex; pIndex--)
                {
                    sum += primes.get(lastIndex);
                }

                int count = lastIndex - firstIndex + 1;
                if (contains(primes, sum) && count > maxCount)
                {
                    maxPrime = sum;
                    maxCount = count;
                    System.out.println(maxPrime);
                    System.out.println(maxCount);
                }
                sum = 0;
            }

            System.out.println(firstIndex);

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
