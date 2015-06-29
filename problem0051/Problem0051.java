package problem0051;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.Digits;
import util.PrimeGenerator;

/**
 *
 *
 *
 * @author micmeyer
 */
public class Problem0051
{
    
    private static final int N = 8;

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        long maxPrime = 10;

        PrimeGenerator pg = new PrimeGenerator();
        List<Long> primes;

        boolean solved = false;
        int index = 0;
        int len = 1;
        long p;
        Map<Integer, int[]> map;
        int counter;
        int[] addends;
        while (!solved)
        {
            primes = pg.generatePrimesSmallerThanN(maxPrime);

            int size = primes.size();

            while (index < size && !solved)
            {
                p = primes.get(index);

                map = getAddends(p, len);

                for (Integer i : map.keySet())
                {
                    addends = map.get(i);

                    for (int a : addends)
                    {
                        counter = 0;

                        for (int m = 1; m < (10 - i) && ((counter + (12 - N)) > (m + i)); m++)
                        {
                            if (contains(primes, p + m * a))
                            {
                                counter++;
                            }
                        }

                        if (counter == (N - 1))
                        {
                            solved = true;
                            System.out.println("Prime: " + p);
                            System.out.println("Addend: " + a);
                        }
                    }
                }

                index++;
            }

//            index = 0; // for considering leading zeros
            
            maxPrime *= 10;
            len++;
        }

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static Map<Integer, int[]> getAddends(Long p, int len)
    {
        Map<Integer, int[]> allAddends = new HashMap<>();

        int[] primeDigits = getDigits(p, len);
        int[] occDigits;
        int[] addends;

        int maxDigit = 11 - N;

        for (int digit = 0; digit < maxDigit; digit++)
        {
            occDigits = getOccurrenceIndices(primeDigits, digit);
            addends = convertOccIndicesToAddends(occDigits);

            allAddends.put(digit, addends);
        }

        return allAddends;
    }

    private static int[] getOccurrenceIndices(int[] digits, int digit)
    {
        int len = digits.length;
        int[] indices = new int[len];

        Arrays.fill(indices, -1);

        int indicesIndex = 0;
        for (int index = 0; index < len - 1; index++)
        {
            if (digits[index] == digit)
            {
                indices[indicesIndex] = len - index - 1;
                indicesIndex++;
            }
        }

        return indices;
    }

    private static int[] convertOccIndicesToAddends(int[] indices)
    {
        if (indices.length == 0)
        {
            return null;
        }

        int digit = indices[0];
        int count = 0;
        for (int index = 1; index < indices.length && digit >= 0; index++)
        {
            count++;
            digit = indices[index];
        }

        int combiCount = 1 << count;
        combiCount--;
        int[] addends = new int[combiCount];
        int c, i, addend;
        for (int combi = 1; combi <= combiCount; combi++)
        {
            c = combi;
            addend = 0;
            for (int index = 0; index < count; index++)
            {
                if ((c & 1) == 1)
                {
                    i = indices[index];
                    addend += tenToThePowerOfN(i);
                }
                c = c >> 1;

            }
            addends[combi - 1] = addend;
        }

        return addends;
    }

    private static int tenToThePowerOfN(int n)
    {
        int pow = 1;
        while (n > 0)
        {
            pow *= 10;
            n--;
        }
        return pow;
    }

    private static int[] getDigits(long n, int len)
    {
        int[] theDigits = new int[len];
        int[] digits = Digits.getDigits(n);
        int size = digits.length;

        while (size > 0 && len > 0)
        {
            theDigits[len - 1] = digits[size - 1];
            len--;
            size--;
        }

        return theDigits;

    }

    private static boolean contains(List<Long> list, Long item)
    {
        return Collections.binarySearch(list, item) >= 0;
    }

}
