package problem0088;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * A natural number, N, that can be written as the sum and product of a given set of at least two natural numbers, {a1, a2, ... , ak} is called a product-sum
 * number: N = a1 + a2 + ... + ak = a1 × a2 × ... × ak.
 * <p>
 * For example, 6 = 1 + 2 + 3 = 1 × 2 × 3.
 * <p>
 * For a given set of size, k, we shall call the smallest N with this property a minimal product-sum number. The minimal product-sum numbers for sets of size, k
 * = 2, 3, 4, 5, and 6 are as follows.
 * <table>
 * <tr><td>k=2: 4</td><td> = 2 × 2 </td><td>= 2 + 2</td></tr>
 * <tr><td>k=3: 6</td><td> = 1 × 2 × 3 </td><td>= 1 + 2 + 3</td></tr>
 * <tr><td>k=4: 8</td><td> = 1 × 1 × 2 × 4 </td><td>= 1 + 1 + 2 + 4</td></tr>
 * <tr><td>k=5: 8</td><td> = 1 × 1 × 2 × 2 × 2 </td><td>= 1 + 1 + 2 + 2 + 2</td></tr>
 * <tr><td>k=6: 12</td><td> = 1 × 1 × 1 × 1 × 2 × 6 </td><td>= 1 + 1 + 1 + 1 + 2 + 6</td></tr>
 * </table>
 * Hence for 2&le;k&le;6, the sum of all the minimal product-sum numbers is 4+6+8+12 = 30; note that 8 is only counted once in the sum.
 * <p>
 * In fact, as the complete set of minimal product-sum numbers for 2&le;k&le;12 is {4, 6, 8, 12, 15, 16}, the sum is 61.
 * <p>
 * What is the sum of all the minimal product-sum numbers for 2&le;k&le;12000?
 *
 * @author micmeyer
 */
public class Problem0088
{
    private static final int TARGET_K = 12000;

    private static final int MAX_MIN_PRODUCT_SUM = 2 * TARGET_K;

    private static final Map<Integer, Integer> MIN_PRODUCT_SUMS = new HashMap<>();

    private static int lowestNonOneIndex;

    /**
     *
     * The first important thing one can see is that we can actually contruct a product-sum
     * for any k we are given:
     * <pre>
     * 1 * 1 * 1 * ... * 1 * 2 * k == 1 + 1 + 1 + ... + 1 + 2 + k
     * </pre>
     * <p>
     * The amount of one's has to be equal to k-2 and we're good. Example:
     * <pre>
     * k = 30
     *
     * 1 * 1 * 1 * ... * 1 * 2 * 30 == 1 + 1 + 1 + ... + 1 + 2 + k
     *
     * 1^(30-2) * 2 * 30 == (30 - 2) + 2 + 30
     *
     * 1 * 2 * 30 == 28 + 2 + 30
     *
     * 60 == 60
     * </pre>
     * From that we can derive that the maximum minimum product-sum of all k's we're
     * interested in will be 2*TARGET_K where TARGET_K is the highest k which is 12000
     * in this case. And that is what MAX_MIN_PRODUCT_SUM represents (see
     * {@link Problem0088#increment(int[])} for more details on that number).
     * <p>
     * Now instead of trying to find the minimum product-sum for each k we iterate
     * through all possible lists of numbers whose product will not exceed our
     * MAX_MIN_PRODUCT_SUM.
     * <p>
     * The trick is that the product is INDEPENDENT of the amount of one's there are
     * while the sum is not. So for each list of non-one numbers there is exactly one
     * right amount of one's we have to add to the list to achieve a product-sum.
     * Example:
     * <pre>
     * list = [2, 3, 3, 6]
     *
     * product = 2*3*3*6 = 108 != 14 = 2+3+3+6 = sum
     * </pre>
     * We see that this is not a product-sum as 108 != 14. But if we add the right amount
     * of one's - in this case 108-14 = 94 - we will have a product-sum:
     * <pre>
     * list = [1 (94 times), 2, 3, 3, 6]
     *
     * product = 1^(94)*2*3*3*6 = 108 == 108 = 1*94+2+3+3+6 = sum
     * </pre>
     * And now we also know the k for that case:
     * <pre>
     * k = [one's] + [non-one's]
     *   = 94 + 4
     *   = 98
     * </pre>
     * So we memorize {98 -> 108} as for k=98 a product-sum is 108. Later on we may stumble
     * across another product-sum for k=98 and then we will just memorize the smaller one,
     * i. e. the minimum.
     * <p>
     * Again in short: Instead of calculating the minimum product-sum for each k, we
     * calculate the k for each product-sum and only memorize the smallest for each k.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        int maxFactors = (int) (Math.log(MAX_MIN_PRODUCT_SUM) / Math.log(2));

        int[] factors = new int[maxFactors];

        for (int t = 0; t < maxFactors - 2; t++)
            factors[t] = 1;

        factors[maxFactors - 2] = 2;
        factors[maxFactors - 1] = 2;
        lowestNonOneIndex = maxFactors - 2;

        for (int k = 2; k <= TARGET_K; k++)
            MIN_PRODUCT_SUMS.put(k, 2 * k);

        calcAllFactors(factors);

        int sum = new HashSet<>(MIN_PRODUCT_SUMS.values()).stream().reduce(0, (a, b) -> a + b);

        System.out.println("Count: " + sum);

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    /**
     *
     * @param factors
     */
    private static void calcAllFactors(int[] factors)
    {
        int product, nonOneSum, missingOnes, currentNonOneCount, k;
        int len = factors.length;

        do
        {
            // Example:
            // factors == [1, 2, 2, 5]
            // product == 20
            product = calcProductFromTo(factors, lowestNonOneIndex, len);

            // nonOneSum == 9
            nonOneSum = calcSumFromTo(factors, lowestNonOneIndex, len);

            /*
             * We want the total sum to be equal to the product,
             * so we need 20-9 = 11 one's to get there
             */
            missingOnes = product - nonOneSum;

            /*
             * currentNonOneCount is the amount of numbers in the
             * array that are not 1. lowerNonOneIndex is the index
             * where the first non-one (from left to right) occurs.
             * In the example:
             * lowestNonOneIndex = 1
             * len = 4
             * currentNonOneCount = 4 - 1 = 3 (i. e. 2, 2 and 5)
             */
            currentNonOneCount = len - lowestNonOneIndex;

            /*
             * The currentNonOneCount plus the missingOnes will
             * give us the k for which the current factors array
             * can produce a product-sum (if the missing one's
             * were added to the factors)
             */
            k = currentNonOneCount + missingOnes;

            /*
             * So the array
             * [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 5]
             * has both a product and a sum of 20
             */
            storeInMapIfInterested(k, product);
        }
        while (increment(factors));
    }

    /**
     *
     * Our goal is to iterate through all possible factorizations and calculate the
     * corresponding k so that it becomes a product-sum by adding the right amount
     * of one's.
     * <p>
     * This is the method where the "next" factorization is generated, basically by
     * incrementing the last number in the array and eventually moving on to the
     * second last number (i. e. decreasing the index), incrementing that one and
     * "reset" that last number.
     * <p>
     * And here is what we need MAX_MIN_PRODUCT_SUM for: How to know when to decrease
     * the index? Answer: MAX_MIN_PRODUCT_SUM tells us which value will definitely
     * not be exceeded by any product-sum in the realm we are searching. So there is
     * no need to look at any factorizations whose product exceeds MAX_MIN_PRODUCT_SUM
     * as we already know that for the corresponding k there is already a better
     * product-sum (as described in {@link Problem0088#main(java.lang.String[])) one
     * solution for each k is always 2*k).
     * <p>
     * So once we incremented a number so much that the product exceeds
     * MAX_MIN_PRODUCT_SUM we can move on the next (actually previous) index and reset
     * all the others to the right.
     * <p>
     * Example:
     * <pre>
     * MAX_MIN_PRODUCT_SUM = 30
     *
     * factors = [1, 2, 3, 3] (product = 1*2*3*3 = 18, OK)
     * {increment}
     * factors = [1, 2, 3, 4] (product = 1*2*3*4 = 24, OK)
     * {increment}
     * factors = [1, 2, 3, 5] (product = 1*2*3*5 = 30, OK)
     * {increment}
     * factors = [1, 2, 3, 6] (product = 1*2*3*6 = 36, NOT OK)
     * {index <= index - 1, increment}
     * factors = [1, 2, 4, 4] (product = 1*2*4*4 = 32, NOT OK)
     * {index <= index - 1, increment}
     * factors = [1, 3, 3, 3] (product = 1*3*3*3 = 27, OK)
     * {index <= lastIndex, increment}
     * factors = [1, 3, 3, 4] (product = 1*3*3*4 = 36, NOT OK)
     * {index <= index - 1, increment}
     * and so on
     * </pre>
     * <p>
     * This is actually not what happens in one call of this method. In fact, everytime
     * it says "OK" in the example above 'true' will be returned as a sign the factorization's
     * product does not exceed MAX_MIN_PRODUCT_SUM. But if this method is called subsequently
     * with the same, unchanged array (which for the sake of this problem is excatly what
     * happens) the algorithm in the example above is the actual process.
     *
     * @param factors
     *
     * @return
     */
    private static boolean increment(int[] factors)
    {
        int len = factors.length;
        int index = len - 1;

        while (index >= 0)
        {
            factors[index]++;

            Arrays.fill(factors, index + 1, len, factors[index]);

            if (calcProductFromTo(factors, lowestNonOneIndex, len) <= MAX_MIN_PRODUCT_SUM)
                return true;

            index--;
            if (index < lowestNonOneIndex)
                lowestNonOneIndex = index;
        }

        return false;
    }

    /**
     *
     * @param factors
     * @param from
     * @param to
     *
     * @return
     */
    private static int calcProductFromTo(int[] factors, int from, int to)
    {
        int product = 1;
        while (from < to)
            product *= factors[from++];
        return product;
    }

    /**
     *
     * @param factors
     * @param from
     * @param to
     *
     * @return
     */
    private static int calcSumFromTo(int[] factors, int from, int to)
    {
        int sum = 0;
        while (from < to)
            sum += factors[from++];
        return sum;
    }

    /**
     *
     * @param k
     * @param product
     */
    private static void storeInMapIfInterested(int k, int product)
    {
        if (k > TARGET_K)
            return;

        int currMin = MIN_PRODUCT_SUMS.get(k);
        if (product < currMin)
            MIN_PRODUCT_SUMS.put(k, product);
    }
}
