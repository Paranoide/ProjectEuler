package problem0049;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.stream.Collectors;
import util.Digits;
import util.PrimeGenerator;

/**
 *
 * The arithmetic sequence, 1487, 4817, 8147, in which each of the terms
 * increases by 3330, is unusual in two ways: (i) each of the three terms are
 * prime, and, (ii) each of the 4-digit numbers are permutations of one another.
 * <br/><br/>
 * There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes,
 * exhibiting this property, but there is one other 4-digit increasing sequence.
 * <br/><br/>
 * What 12-digit number do you form by concatenating the three terms in this
 * sequence?
 *
 * @author micmeyer
 */
public class Problem0049
{

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        List<Long> primes = new PrimeGenerator()
                .generatePrimesSmallerThanN(10000)
                .stream()
                .filter(p -> (p > 1000))
                .collect(Collectors.toList());

        List<SortedSet<Long>> allPerms = getAllPrimePermutations(primes);

        List<List<Long>> result = getPermsWithSameDifferences(allPerms);
        
        System.out.println(result);
        
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static List<List<Long>> getPermsWithSameDifferences(List<SortedSet<Long>> permutations)
    {
        List<List<Long>> theResults = new ArrayList<>();
        List<Long> permsList;
        Long diff;
        Integer val;
        Map<Long, Integer> count;

        for (SortedSet<Long> perms : permutations)
        {
            count = new HashMap<>();
            permsList = new ArrayList<>(perms);
            for (int a = 0; a < permsList.size() - 1; a++)
            {
                for (int b = a + 1; b < permsList.size(); b++)
                {
                    diff = permsList.get(b) - permsList.get(a);
                    val = count.putIfAbsent(diff, 1);
                    if (val != null)
                    {
                        val++;
                        count.put(diff, val);
                        System.out.println(val + " : " + diff);
                        if (val == 2)
                        {
                            List<Long> theResult
                                    = determinePrimesWithSameDifference(permsList, diff, permsList.get(a));
                            theResults.add(theResult);
                            System.out.println(perms);
                            System.out.println(theResult);
                            System.out.println();
                        }
                    }
                }
            }
        }

        return theResults;
    }

    private static List<Long> determinePrimesWithSameDifference(List<Long> perms, Long diff, Long p)
    {
        Long first = perms.get(0);
        Long last = perms.get(perms.size() - 1);
        List<Long> result = new ArrayList<>();

        Long l = p - diff;
        while (l >= first)
        {
            if (perms.contains(l))
            {
                result.add(l);
            }
            l -= diff;
        }

        l = p + diff;
        while (l <= last)
        {
            if (perms.contains(l))
            {
                result.add(l);
            }
            l += diff;
        }

        return result;
    }

    private static List<SortedSet<Long>> getAllPrimePermutations(List<Long> primes)
    {
        List<SortedSet<Long>> allPerms = new ArrayList<>();
        SortedSet<Long> perms;
        Long prime;
        while (!primes.isEmpty())
        {
            prime = primes.remove(0);
            perms = getPrimePermutations(prime, primes);
            if (perms.size() >= 3)
            {
                allPerms.add(perms);
            }
        }

        return allPerms;
    }

    private static SortedSet<Long> getPrimePermutations(long n, List<Long> primes)
    {
        SortedSet<Long> perms;

        SortedSet<Long> primePerms = new TreeSet<>();
        perms = generatePermutations(n);
        for (Long p : perms)
        {
            if (contains(primes, p))
            {
                primePerms.add(p);
                primes.remove(p);
            }
        }

        return primePerms;
    }

    private static boolean contains(List<Long> list, Long item)
    {
        return Collections.binarySearch(list, item) >= 0;
    }

    private static SortedSet<Long> generatePermutations(long n)
    {
        SortedSet<Long> perms = new TreeSet<>();
        int[] digits = Digits.getDigits(n);
        int[] curr = new int[digits.length];
        generatePermutations(digits, curr, perms, 0);
        return perms;
    }

    private static void generatePermutations(int[] digits, int[] curr, SortedSet<Long> inOutSet, int depth)
    {
        if (depth == digits.length)
        {
            inOutSet.add(Digits.arrayToLong(curr));
        }
        else
        {
            for (int d = 0; d < digits.length; d++)
            {
                int digit = digits[d];
                if (digit >= 0)
                {
                    curr[depth] = digit;
                    digits[d] = -1;
                    generatePermutations(digits, curr, inOutSet, depth + 1);
                    digits[d] = digit;
                }
            }
        }
    }
}
