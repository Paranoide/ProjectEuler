package de.michel.projecteuler.problems0051_0100.problem0095;

import de.michel.projecteuler.util.Divisors;

import java.util.*;

/**
 * @author micmeyer
 */
public class Problem0095
{
    private static final int TARGET = 1_000_000;

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        executeProblem0095();

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static void executeProblem0095()
    {
        Map<Integer, Integer> sumPerN = new HashMap<>();

        for (int n = 1; n <= TARGET; n++)
        {
            int sum = getSumOfProperDivisors(n);

            if (sum <= TARGET)
                sumPerN.put(n, sum);
        }

        Chain longestChain = new Chain(Collections.emptySet());
        for (int k = 1; k <= TARGET; k++)
        {
            Chain chain = getChain(k, sumPerN);

            if (chain.length() > longestChain.length())
                longestChain = chain;

            for (Integer key : chain.keys)
                sumPerN.remove(key);
        }

        System.out.println("Longest chain: " + longestChain);
        System.out.println();
        System.out.println("Result: " + longestChain.keys.stream().mapToInt(Integer::intValue).min().orElse(-1));
    }

    private static int getSumOfProperDivisors(int n)
    {
        List<Long> allProperDivisors = Divisors.getAllProperDivisors(n);

        return allProperDivisors.stream().mapToInt(Long::intValue).sum();
    }

    private static Chain getChain(int startN, Map<Integer, Integer> sumPerN)
    {
        Set<Integer> keys = new HashSet<>();

        int n = startN;

        do
        {
            if (!sumPerN.containsKey(n))
                return new Chain(keys);

            if (keys.contains(n))
                return new Chain(Set.of(startN));

            keys.add(n);
            n = sumPerN.get(n);
        }
        while (n != startN);

        return new Chain(keys, keys.size());
    }

    private static class Chain
    {
        private final Set<Integer> keys;
        private final int chainLength;

        public Chain(Set<Integer> keys)
        {
            this.keys = keys;
            this.chainLength = 0;
        }

        public Chain(Set<Integer> keys, int chainLength)
        {
            this.keys = keys;
            this.chainLength = chainLength;
        }

        private int length()
        {
            return this.chainLength;
        }

        @Override
        public String toString()
        {
            return "Chain{" +
                    "keys=" + keys +
                    ", chainLength=" + chainLength +
                    '}';
        }
    }
}
