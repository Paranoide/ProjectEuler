package problem0078;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Let p(n) represent the number of different ways in which n coins can be separated into piles. For example, five coins can be separated into piles in exactly
 * seven different ways, so p(5)=7.
 * <pre>
 * OOOOO
 * OOOO O
 * OOO OO
 * OOO O O
 * OO OO O
 * OO O O O
 * O O O O O
 * </pre>
 * Find the least value of n for which p(n) is divisible by one million.
 *
 * @author micmeyer
 */
public class Problem0078
{
    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;

    private static final BigInteger TARGET_DIVISOR = new BigInteger("1000000");

    private static final Map<Integer, BigInteger> CACHE = new HashMap<>();

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        BigInteger result = ONE;
        int n = 3;

        while (!result.mod(TARGET_DIVISOR).equals(ZERO))
            result = p(n++);

        System.out.println("Result: " + (n - 1));
        System.out.println();
        System.out.println("(the number of different ways for " + (n - 1) + " because it is a ridiculously large number:)");
        System.out.println(result);
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static BigInteger p(int n)
    {
        if (n < 0)
            return ZERO;

        if (n < 2)
            return ONE;

        BigInteger result = CACHE.get(n);

        if (result == null)
            result = ZERO;
        else
            return result;

        int n_1, n_2;

        for (int k = 1; k <= n; k++)
        {
            n_1 = n - k * (3 * k - 1) / 2;
            n_2 = n - k * (3 * k + 1) / 2;

            if (n_1 < 0)
                return saveResult(n, result);

            if (k % 2 == 0)
                result = result.subtract(p(n_1).add(p(n_2)));
            else
                result = result.add(p(n_1).add(p(n_2)));
        }

        return saveResult(n, result);
    }

    private static BigInteger saveResult(int n, BigInteger i)
    {
        CACHE.put(n, i);
        return i;
    }
}
