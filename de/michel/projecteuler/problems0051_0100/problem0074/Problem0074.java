package de.michel.projecteuler.problems0051_0100.problem0074;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * The number 145 is well known for the property that the sum of the factorial of its digits is equal to 145:
 * <p>
 *
 * 1! + 4! + 5! = 1 + 24 + 120 = 145
 * <p>
 *
 * Perhaps less well known is 169, in that it produces the longest chain of numbers that link back to 169; it turns out that there are only three such loops
 * that exist:
 *
 * <pre>
 * 169 → 363601 → 1454 → 169
 * 871 → 45361 → 871
 * 872 → 45362 → 872</pre>
 *
 * It is not difficult to prove that EVERY starting number will eventually get stuck in a loop. For example,
 *
 * <pre>
 * 69 → 363600 → 1454 → 169 → 363601 (→ 1454)
 * 78 → 45360 → 871 → 45361 (→ 871)
 * 540 → 145 (→ 145)</pre>
 *
 * Starting with 69 produces a chain of five non-repeating terms, but the longest non-repeating chain with a starting number below one million is sixty
 * terms.<p>
 *
 * How many chains, with a starting number below one million, contain exactly sixty non-repeating terms?
 *
 * @author micmeyer
 */
public class Problem0074
{
    private static int result = 0;

    /**
     * Well, let me tell you a story...
     *
     * What is the main idea of this solution?
     * We could just iterate through all numbers from 0 to 999.999 and check their digit factorial chain length.
     * But there are so many duplicates as every permutation of a number maps to the same digit factorial sum, like
     * all numbers in [123, 132, 213, 231, 312, 321] (maps always to 1! + 2! + 3! = 8).
     *
     * As it turns out, if we could eliminate all those duplicates in the given range (0 to 999.999) we end up with
     * only 5005 "digit unique" numbers! And for these we have to check the digit factorial chain length as mentioned
     * in the problem description.
     *
     * So instead of iterating through all the numbers, we generate all combinations of digits in a number of a
     * certain length. For example, for a number length of 3 we can have
     *
     * [3 zeros, 0 ones, 0 twos, 0 threes, ...] or
     * [2 zeros, 1 one, 0 twos, 0 threes, ...] or
     * [1 zero, 1 one, 1 two, 0 threes, ...] and so on.
     *
     * Each of these lists is a representation for a group of numbers. For instance, the second list in the examples
     * represents 100 (but not 010 or 001 as we can't have leading zeros; more about that in a second) and the last
     * list represents 120, 210, 102 and 201 (again, not 012 or 021).
     *
     * The elimination of numbers with leading zeros is covered by the isValid() method which gives some details and
     * explanation in its JavaDoc. In short: Only the one-digit zero (0) will be valid, all others (like 00 or 000)
     * will be discarded.
     *
     * Now, for each of those lists (or, to be more precise, arrays) the digit factorial chain length is computed
     * which is pretty simple and straightforward and is done in the determineDigitFactorialChainLength() method.
     * Once this method gives us exactly 60 (as specified in the problem description) it gets really interesting:
     *
     * As we have discarded all the permutation duplicates earlier we now have to determine the AMOUNT of numbers
     * with this particular set of digits. This is what getPermutationCount() does and is a little bit more
     * sophisticated. Again, its JavaDoc gives further explanations.
     *
     * Once this amount is computed, we only have to add up all these amounts and finally end up with the
     * wanted result.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        Buckets buckets = new Buckets(10);

        for (int nrOfDigits = 1; nrOfDigits <= 6; nrOfDigits++)
        {
            List<int[]> distributions = buckets.distribute(nrOfDigits);

            distributions.stream().filter(distribution -> isValid(distribution)).forEachOrdered(distribution ->
            {
                int chainLength = determineDigitFactorialChainLength(distribution);
                if (chainLength == 60)
                    result += getPermutationCount(distribution);
            });
        }

        System.out.println("Result: " + result);
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    /**
     * Calculates the number of distinct permutations of the
     * digits of the given number (represented by the countPerDigits
     * array) without leading zeros.
     *
     * For example, the number 1120 can be permutated to
     *
     * 1120, 1102, 1210, 1201, 2110, 2101, 2011, 1021, 1012
     *
     * and no more because the 0 cannot be moved to the front
     * and the two ones can't be switched among each other
     * as it wouldn't create a new distinct number.
     *
     * So the result would be 9 (and not 4! = 24 as might be
     * expected!).
     *
     * The calculation works as follows:
     *
     * Let
     * z be the amount of zero digits,
     * n be the amount of all non-zero digits added up and
     *
     * First we just assume that all non zero-digits are unique
     * which would give us n! as the permutation count for all
     * the non-zero digits.
     * This count will be divided by the factorial of the count
     * of each digit. For example, if we have 12123, the array
     * will be
     * [0, 2, 2, 1, 0, 0, 0, 0, 0, 0]
     * and we will have
     * count = 5! / (2! * 2! * 1!) = 120 / 4 = 30
     *
     * That is because if a digit has more than one occurence all
     * the permutations among this digit result in the same amount
     * of duplicates of the whole number. For example, if we have
     * 1112, if all the three ones were unique we would have
     *
     * 6x 1112
     * 6x 1121
     * 6x 1211
     * 6x 2111
     * -------
     * 24 = 4! permutations.
     *
     * Why 6 times each? Because we have THREE ones and 3! = 6.
     *
     * The same goes for 12123, where we have TWO ones (so divide
     * by 2!), TWO twos (again by 2!) and ONE three (divide by 1!
     * or by 1 or just not at all).
     *
     * Finally we have to put in all the zeros we just ignored so far.
     * As we can't have leading zeros we just ignore the first digit
     * and try to put the zeros somewhere between (or around) the
     * remaining digit. For example, given 5441000 we ignore the 5
     * which leads us to 441000 and now we need the number of
     * possibilities for the positions of the zeros.
     *
     * We can ignore the actual digit values, we just need to know that
     * they are non-zero, so let's mark them with an x:
     *
     * xxx000
     * xx0x00
     * x0xx00
     * 0xxx00
     *
     * and so on are the possible positions. The amount is computed by
     * (6 choose 3) = 20 in this case, or more generally by
     * (m choose z) where (m := n + z - 1) which is the total number
     * of digits minus one.
     *
     * If we now mulitply the number of permutations without the zeros
     * with the number of possible positions of zeros we end up with
     * the final permutation count.
     *
     * For the example of 5441000 it is:
     *
     * count = 4! / (1! * 2! * 1!) = 24 / 2 = 12
     * m choose z = (6 choose 3) = 20
     *
     * result = 12 * 20 = 240
     *
     * @param countPerDigits
     * @return
     */
    private static int getPermutationCount(int[] countPerDigits)
    {
        int z = countPerDigits[0];
        int n = 0;
        for (int t = 1; t < 10; t++)
            n += countPerDigits[t];

        int count = factorial(n);
        for (int t = 1; t < 10; t++)
            count /= factorial(countPerDigits[t]);

        return count * nChooseK(n + z - 1, z);
    }

    /**
     * Determines the factorial chaing length of a given count per digit array.
     *
     * @param countPerDigit
     * @return
     */
    private static int determineDigitFactorialChainLength(int[] countPerDigit)
    {
        Set<Integer> numbersInChain = new HashSet<>();
        int length = 0;

        int n = countPerDigitToANumber(countPerDigit);

        while (!numbersInChain.contains(n))
        {
            numbersInChain.add(n);
            n = getFactorialSum(n);

            length++;
        }

        return length;
    }

    /**
     * Converts the array that gives the count per digit of a number
     * to an acutal number containing these digits. All digits are
     * just put behind one another in descending order so that
     * no leading zeros will occur.
     *
     * Note that this is only ONE possible number representation of
     * the array.
     *
     * @param countPerDigit
     * @return
     */
    private static int countPerDigitToANumber(int[] countPerDigit)
    {
        int aNumber = 0;

        for (int d = countPerDigit.length - 1; d >= 0; d--)
        {
            for (int t = 0; t < countPerDigit[d]; t++)
            {
                aNumber = 10 * aNumber + d;
            }
        }

        return aNumber;
    }

    /**
     * Calculates the sum of all the digit factorials of
     * a given number
     *
     * @param n
     * @return
     */
    private static int getFactorialSum(long n)
    {
        if (n < 10)
            return factorial((int) n);

        return getFactorialSum(n / 10) + factorial((int) (n % 10));
    }

    /**
     * Some contant factorials as higher values won't be needed
     * in this problem.
     *
     * @param n
     * @return
     */
    private static int factorial(int n)
    {
        switch (n)
        {
            case 0:
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 6;
            case 4:
                return 24;
            case 5:
                return 120;
            case 6:
                return 720;
            case 7:
                return 5040;
            case 8:
                return 40320;
            case 9:
                return 362880;
        }
        throw new IllegalArgumentException("No value specified for " + n);
    }

    private static int nChooseK(int n, int k)
    {
        int nChooseK = 1;

        for (int t = 0; t < k; t++)
            nChooseK *= (n - t);

        return nChooseK / factorial(k);
    }

    /**
     * A countPerDigit array will always convert to 0 if
     * every digit except 0 has a count of 0 (i.e. all entries
     * at the indices 1-9 are zero) regardless of the count
     * for 0 (i.e. the index 0).
     *
     * For instance
     *
     * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
     * [1, 0, 0, 0, 0, 0, 0, 0, 0, 0]
     * [2, 0, 0, 0, 0, 0, 0, 0, 0, 0]
     * [9, 0, 0, 0, 0, 0, 0, 0, 0, 0]
     *
     * will all convert to 0.
     *
     * We declare such an array to be non-valid if and only if
     * the first entry is not 1 but all other entries are 0.
     *
     * So e.g. the arrays
     *
     * [1, 0, 0, 0, 0, 0, 0, 0, 0, 0]
     * [1, 0, 4, 0, 5, 3, 1, 1, 0, 0]
     * [0, 0, 0, 0, 0, 0, 0, 0, 3, 2]
     * [3, 0, 1, 0, 0, 0, 0, 0, 0, 0]
     *
     * are valid but
     *
     * [2, 0, 0, 0, 0, 0, 0, 0, 0, 0]
     * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
     * [9, 0, 0, 0, 0, 0, 0, 0, 0, 0]
     *
     * are not.
     *
     * We do that because in this problem we can't have leading
     * zeros and every other representation (e.g. a 3 at index 0)
     * would be a zero with leading zeros.
     *
     * @param countPerDigit
     * @return
     */
    private static boolean isValid(int[] countPerDigit)
    {
        if (countPerDigit[0] == 1)
            return true;

        for (int t = 1; t < 10; t++)
            if (countPerDigit[t] != 0)
                return true;

        return false;
    }
}
