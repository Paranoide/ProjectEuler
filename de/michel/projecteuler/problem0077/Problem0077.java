package de.michel.projecteuler.problem0077;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import de.michel.projecteuler.util.PrimeGenerator;

/**
 *
 * It is possible to write ten as the sum of primes in exactly five different ways:
 * <pre>
 * 7 + 3
 * 5 + 5
 * 5 + 3 + 2
 * 3 + 3 + 2 + 2
 * 2 + 2 + 2 + 2 + 2
 * </pre>
 * What is the first value which can be written as the sum of primes in over five thousand different ways?
 *
 * @author micmeyer
 */
public class Problem0077
{

    private static final int TARGET = 5000;

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        PrimeGenerator pg = new PrimeGenerator();
        List<Long> primes;

        AmountMap amountMap = new AmountMap();

        int n, count = 0, amount;

        for (n = 4; count < TARGET + 2; n++)
        {
            count = 0;

            primes = pg.generatePrimesSmallerThanN(n + 1);

            for (long _prime : primes)
            {
                int prime = (int) _prime;

                amount = amountMap.get(n - prime, prime);

                if (amount > 0)
                {
                    count += amount;
                    amountMap.put(n, prime, count);
                }
            }
        }

        System.out.println("Result: " + (n - 1));
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static class AmountMap
    {
        private final Map<Integer, TreeMap<Integer, Integer>> MAP = new HashMap<>();

        public AmountMap()
        {
            MAP.put(1, new TreeMap<>());
            MAP.put(2, new TreeMap<>());
            MAP.put(3, new TreeMap<>());

            MAP.get(2).put(2, 1);
            MAP.get(3).put(3, 1);
        }

        public void put(int n, int maxFirstAddend, int amount)
        {
            TreeMap<Integer, Integer> amountPerMaxFirstAddend = MAP.get(n);

            if (amountPerMaxFirstAddend == null)
                amountPerMaxFirstAddend = new TreeMap<>();

            amountPerMaxFirstAddend.put(maxFirstAddend, amount);

            MAP.put(n, amountPerMaxFirstAddend);
        }

        public int get(int rest, int maxFirstAddend)
        {
            if (rest == 0)
                return 1;

            TreeMap<Integer, Integer> amountPerMaxFirstAddend = MAP.get(rest);

            if (amountPerMaxFirstAddend == null)
                throw new IllegalStateException("amountPerMaxFirstAddend is null, the given n (= " + rest + ") is probably too high.");

            Iterator<Integer> itMaxFirstAddend = amountPerMaxFirstAddend.descendingKeySet().iterator();

            if (!itMaxFirstAddend.hasNext())
                return 0;

            int firstAddend = itMaxFirstAddend.next();

            while (itMaxFirstAddend.hasNext() && firstAddend > maxFirstAddend)
                firstAddend = itMaxFirstAddend.next();

            if (firstAddend > maxFirstAddend)
                return 0;

            return amountPerMaxFirstAddend.get(firstAddend);
        }

        @Override
        public String toString()
        {
            return this.MAP.toString();
        }
    }
}
