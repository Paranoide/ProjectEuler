package de.michel.projecteuler.problems0051_0100.problem0075;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import de.michel.projecteuler.util.Divisors;

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
    private static final int L = 1500000;

    /**
     * The whole idea goes in three steps:
     *
     * 1. Generate all primitive Pythagorean triples ("primitives") that have a circumference ("lenghts") of a maximum of L (= 1,500,000).
     * While doing that remember those lengths that have more than one primitive as they have to be removed at the end (stored in a list
     * called "toRemove").
     *
     * 2. Create all multiples of these pritimives that have a circumference of a maximum of L (= 1,500,000). These will be stored in a
     * list called "toAdd" as they have to be added at the end. Also - like in step 1 - those lenghts that come up to have more than one
     * Pythagorean triple (regardless of whether primitive or non-primtive) will be stored in the list "toRemove".
     *
     * 3. Finally take the list of primtives, add the triples that have to be added (from "toAdd") and remove those that are to be removed
     * (from "toRemove") afterwards. Now that list contains exactly the triples that were searched in the problem description.
     *
     * Comments:
     * We don't have to actually store the triples but only the lengths of these triplets. The exact side lengths aren't of any interest
     * for this problem. If we have the primtive (3, 4, 5) (length = 12) and create the mutltiples (6, 8, 10), (9, 12, 15), ... the
     * lengths are directly proportional (24, 36, ...) to the primtive length. So instead of creating multiples of the triples we just
     * create multiples of the lenghts.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        Set<Integer> toAdd = new HashSet<>();
        Set<Integer> toRemove = new HashSet<>();

        // Step 1
        List<Integer> primitiveLengthsList = generatePrimitives(L, toRemove);

        // Step 2
        createMultiplesOfPrimitives(L, primitiveLengthsList, toAdd, toRemove);

        // Step 3
        SortedSet<Integer> results = createFinalResults(primitiveLengthsList, toAdd, toRemove);

        System.out.println("Result: " + results.size());
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    /**
     * Generates all primitive Pythagorean triples ("primitives") that have a circumference ("lengths") of a maximum
     * of L, stores these lengths in a List and returns that List. In addition it stores all duplicates (i.e. lengths
     * that have more than one primitive) into the passed Set duplicates.<p>
     *
     * To generate the triples Euclid's formula is used (Source:
     * https://en.wikipedia.org/w/index.php?title=Pythagorean_triple&oldid=798614380, section 2: Generating a triple)
     * .<p>
     *
     * It states that for each pair of m and n with m > n > 0, with exactly one of them even and the other one odd
     * and with m and n being coprime (i.e. gcd(m, n) == 1) the following formulae will then generate a primitive
     * Pythagorean triple (a, b, c):
     * <pre>
     * a = m * m - n * n;
     * b = 2 * m * n;
     * c = m * m + n * n;
     * </pre>
     * So we let m go from 1 to a reasonable maximum (maxM) and let n go from 1 to m-1. The article also states that
     * EVERY primitive triple arises by doing that, so none is skipped and eventually every primitive is caught.<p>
     *
     * maxM is set to sqrt(L) due to a simple approximation:<br>
     * c = m² + n².<br>
     * With m = sqrt(L): c = L + n² > L.<br>
     * So c alone will be greater than L and thus a+b+c > L. That's why maxM = sqrt(L) will definitely be sufficient.
     * Of course there is a better maxM but for now it was not worth it to calculate an even better maximum.
     *
     * @param L The maxiumum length
     * @param duplicates Out-parameter which stores the duplicates (i.e. lengths that have more than one primitive)
     * @return The lengths lower than or equal to L that have at least one primitive Pythagorean triple in
     * ASCENDING order
     */
    private static List<Integer> generatePrimitives(final int L, Set<Integer> duplicates)
    {
        /*
         * Has to be a SortedSet as the return value will be a List with the primitive lengths
         * in ascending order.
         */
        SortedSet<Integer> primitiveLengthsSet = new TreeSet<>();

        int a, b, c;

        int maxM = (int) (Math.sqrt(L));

        for (int m = 1; m <= maxM; m++)
        {
            for (int n = 1; n < m; n++)
            {
                a = m * m - n * n;
                b = 2 * m * n;
                c = m * m + n * n;

                /*
                 * If the length is greater than L continue with the next m (!).
                 * We can do that because the length will only get bigger when
                 * m is contant and n get bigger as the sum is:
                 *
                 * a+b+c = m²-n² + 2*n*m + m²+n² = 2m² + 2*n*m
                 *
                 * 2m² is constant and 2*n*m gets bigger when n get bigger!
                 */
                if (a + b + c > L)
                    break;

                /*
                 * The premises for generating primtives: One has to be odd
                 * and the other even and they have to be coprime.
                 */
                if (((m % 2 == 0) ^ (n % 2 == 0)) && Divisors.areCoprime(m, n))
                {
                    int length = a + b + c;

                    if (!primitiveLengthsSet.contains(length))
                        primitiveLengthsSet.add(length);
                    else
                        duplicates.add(length);
                }
            }
        }

        return new ArrayList<>(primitiveLengthsSet);
    }

    /**
     *
     * Takes the primtitive lengths and creates all multiples of each one that are lower than or equal to L. For each of those multiples (i.e. non-primitive)
     * there can be one of three cases:
     * <p>
     *
     * 1. This non-primitive length is already contained by the primitiveLengthList. For example, 84 has the primitive (12, 35, 37) but also the
     * non-primitive (21, 28, 35) which is a multiple of (3, 4, 5) (with a length of 12). So the 84 will be added to the Set "toRemove" as it has
     * more than one triple.<br>
     * Note: We can NOT remove it from the primitiveLengthList as we HAVE to create the multiples for the 84 because it is also a primitive! If we remove
     * it from the List there might be lengths that ought to be put into the Set "toRemove" but because of not creating the multiples for 84 these numbers
     * might mistakenly be kept.<p>
     *
     * 2. This non-primitive length is neither contained by the primitiveLengthList nor by the Set "toAdd". So it's the first time we come across this
     * length and thus it has to be added later on so it's added to the Set "toAdd"
     * .<p>
     *
     * 3. This non-primitive length is already contained by the Set "toAdd". There is no primitive for this length but we have already come across this
     * length as a multiple of an earlier primitive. So now we have to add it to the Set "toRemove" as this length now has at least two triples.<br>
     * Note: Again, we can't remove it from the primitiveLengthList and neither can it be removed from the Set "toAdd". We have to remember that it was
     * already put into "toAdd" and then again removed in order to not put it into "toAdd" again later on. That's why we need that little extra information
     * from the Set "toRemove"
     * .<p>
     *
     * (4.) One might think of the 4th case when this non-primitive is even already in the Set "toRemove". It actually doesn't matter now if this non-primitive
     * is already contained in the other Set or List and it doesn't matter what will happen next because of the way the Set "toRemove" will be used later on.
     * See the documentation for {@link Problem0075#createFinalResults(java.util.List, java.util.Set, java.util.Set)} for more information.
     *
     * @param L The maximum length
     * @param primitiveLengthsList A List with the primitive lengths that have to be in ascending order.
     * @param toAdd Out-parameter that stores the lengths that have to be added later on.
     * @param toRemove Out-parameter that stores the lengths that have to be removed later on.
     */
    private static void createMultiplesOfPrimitives(final int L, List<Integer> primitiveLengthsList, Set<Integer> toAdd, Set<Integer> toRemove)
    {
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
    }

    /**
     * Simply takes the primitive lengths, adds the lengths from toAdd and removes the lengths from toRemove afterwards.
     * It is very IMPORTANT to do this in that exact order. The Set "toRemove" contains lengths that might be contained
     * by the primitiveLengths AS WELL AS by the Set "toAdd". So if we remove the lengths from "toRemove" first and THEN
     * add the lengths from "toAdd" we end up with too much (Step 1 and Step 2 prepared the List and the Sets in exactly
     * that way).
     *
     * @param primitiveLengths The primitive lengths
     * @param toAdd The lengths that have to be added
     * @param toRemove The lengths that have to be removed
     * @return
     */
    private static SortedSet<Integer> createFinalResults(List<Integer> primitiveLengths, Set<Integer> toAdd, Set<Integer> toRemove)
    {
        SortedSet<Integer> results = new TreeSet<>();

        results.addAll(primitiveLengths);
        results.addAll(toAdd);
        results.removeAll(toRemove);

        return results;
    }
}
