package problem0075;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import util.Divisors;

/**
 *
 * It turns out that 12 cm is the smallest length of wire that can be bent to form an integer sided right angle triangle in exactly one way, but there are many
 * more examples.
 *
 * <pre>
 * 12 cm: (3,4,5)
 * 24 cm: (6,8,10)
 * 30 cm: (5,12,13)
 * 36 cm: (9,12,15)
 * 40 cm: (8,15,17)
 * 48 cm: (12,16,20)
 * </pre>
 *
 * In contrast, some lengths of wire, like 20 cm, cannot be bent to form an integer sided right angle triangle, and other lengths allow more than one solution
 * to be found; for example, using 120 cm it is possible to form exactly three different integer sided right angle triangles.<p>
 *
 * 120 cm: (30,40,50), (20,48,52), (24,45,51)
 * <p>
 *
 * Given that L is the length of the wire, for how many values of L ≤ 1,500,000 can exactly one integer sided right angle triangle be formed?
 *
 * @author micmeyer
 */
public class Problem0075
{
    private static final int L = 1500000; //1500000;

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        int a, b, c;

        SortedSet<Integer> primitiveLengthsSet = new TreeSet<>();
        Set<Integer> toAdd = new HashSet<>();
        Set<Integer> toRemove = new HashSet<>();

        int maxM = (int) (Math.sqrt(L) + 1);

        for (int m = 1; m <= maxM; m++)
        {
            for (int n = 1; n < m; n++)
            {
                a = m * m - n * n;
                b = 2 * m * n;
                c = m * m + n * n;

                if (a + b + c > L)
                    continue;

                if (((m % 2 == 0) ^ (n % 2 == 0)) && Divisors.areCoprime(m, n))
                {
                    int length = a + b + c;

                    if (!primitiveLengthsSet.contains(length))
                        primitiveLengthsSet.add(length);
                    else
                        toRemove.add(length);
                }
            }
        }

        List<Integer> primitiveLengthsList = new ArrayList<>(primitiveLengthsSet);

        int primLen, currLen;

        for (int index = 0; index < primitiveLengthsList.size(); index++)
        {
            primLen = primitiveLengthsList.get(index);
            currLen = 2 * primLen;

            while (currLen <= L)
            {
                boolean primitiveLengthsContains = Collections.binarySearch(primitiveLengthsList, currLen) >= 0;

                if (primitiveLengthsContains)
                    toRemove.add(currLen);
                else
                {
                    if (!toAdd.contains(currLen))
                        toAdd.add(currLen);
                    else
                        toRemove.add(currLen);
                }

                currLen += primLen;
            }
        }

        Set<Integer> results = new TreeSet<>();

        results.addAll(primitiveLengthsList);
        results.addAll(toAdd);
        results.removeAll(toRemove);

        System.out.println("Result: " + results.size());
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }
}
