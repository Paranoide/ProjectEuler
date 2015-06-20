package problem0044;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * Pentagonal numbers are generated by the formula, Pn=n(3n−1)/2. The first ten
 * pentagonal numbers are:
 * <pre>
 * 1, 5, 12, 22, 35, 51, 70, 92, 117, 145, ...
 * </pre>
 * It can be seen that P4 + P7 = 22 + 70 = 92 = P8. However, their difference,
 * 70 − 22 = 48, is not pentagonal.<br/>
 * <br/>
 * Find the pair of pentagonal numbers, P_j and P_k, for which their sum and
 * difference are pentagonal and D = |P_k − P_j| is minimised; what is the value
 * of D?
 *
 * @author micmeyer
 */
public class Problem0044
{

    private static final List<Long> PENTAGONS = new ArrayList<>();

    private static int lastSize = 0;

    private static long currMin = Integer.MAX_VALUE;

    static
    {
        PENTAGONS.add(1L);
        PENTAGONS.add(5L);
        PENTAGONS.add(12L);
        PENTAGONS.add(22L);
        PENTAGONS.add(35L);
        PENTAGONS.add(51L);
    }

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();
        
        while (!lookForSolutions());

        System.out.println("Min: " + currMin);

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static boolean lookForSolutions()
    {
        int size = PENTAGONS.size();
        if (currMin < 3 * lastSize + 1)
        {
            return true;
        }

        addEnoughPentagons();

        for (int a = 0; a < size - 1; a++)
        {
            long p1 = PENTAGONS.get(a);

            long firstNewOne = PENTAGONS.get(lastSize);
            int startB = (p1 > (firstNewOne - currMin)) ? Math.max(a + 1, lastSize) : size;

            for (int b = startB; b < size; b++)
            {
                long p2 = PENTAGONS.get(b);

                long diff = p2 - p1;

                if (diff > currMin)
                {
                    break;
                }

                long sum = p1 + p2;

                boolean plus = contains(sum);
                boolean minus = contains(diff);

                if (plus && minus)
                {
                    if (diff < currMin)
                    {
                        currMin = diff;
                    }
                }

            }
        }

        lastSize = size;

        return false;
    }

    private static void addEnoughPentagons()
    {
        int size = PENTAGONS.size();
        long last1 = PENTAGONS.get(size - 1);
        long last2 = PENTAGONS.get(size - 2);

        long newLast = last1;
        while (newLast < (last1 + last2))
        {
            newLast = newLast + 3 * size + 1;
            PENTAGONS.add(newLast);
            size++;
        }
    }

    private static boolean contains(long n)
    {
        return Collections.binarySearch(PENTAGONS, n) >= 0;
    }

}
