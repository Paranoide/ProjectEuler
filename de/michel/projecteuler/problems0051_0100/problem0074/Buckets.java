package de.michel.projecteuler.problems0051_0100.problem0074;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO Some description here...
 *
 * @author Michel Meyer <micmeyer@uos.de>
 */
public class Buckets
{
    private static final Condition NO_CONDITION = (int n, int k, int index, int[] currentBuckets) -> true;

    private final int n;

    private final int[] buckets;

    private List<int[]> allPossibleBuckets;

    public Buckets(int n)
    {
        this.n = n;
        this.buckets = new int[n];
    }

    /**
     * Use this method if the number of possibilities is small enough
     * to be stored in a list. Otherwise use distribute(int, ActionPerBucketDistribution).
     *
     * @param k How many "points" to distribute between the buckets
     * @return A List of all the possbile buckets containing the k distributed points
     */
    public List<int[]> distribute(int k)
    {
        return this.distribute(k, NO_CONDITION);
    }

    public List<int[]> distribute(int k, Condition preCondition)
    {
        this.allPossibleBuckets = new ArrayList<>();

        ActionPerBucketDistribution action = (int[] buckets1) ->
        {
            int[] bucketsCopy = new int[buckets1.length];
            System.arraycopy(buckets1, 0, bucketsCopy, 0, buckets1.length);
            allPossibleBuckets.add(bucketsCopy);
        };

        this.distribute(k, action, preCondition);

        return this.allPossibleBuckets;
    }

    /**
     * Iteratively computes all the distributions for the given k "points"
     * and executes the given action for each distribution.
     *
     * @param k
     * @param action
     */
    public void distribute(int k, ActionPerBucketDistribution action)
    {
        this.distribute(k, 0, action, NO_CONDITION);
    }

    public void distribute(int k, ActionPerBucketDistribution action, Condition preCondition)
    {
        this.distribute(k, 0, action, preCondition);
    }

    private void distribute(int k, int index, ActionPerBucketDistribution action, Condition preCondition)
    {
        if (!preCondition.accept(this.n, k, index, this.buckets))
            return;

        if (index == this.buckets.length)
        {
            action.action(this.buckets);
        }
        else if (index == this.buckets.length - 1)
        {
            this.buckets[index] = k;
            this.distribute(k, index + 1, action, preCondition);
        }
        else
        {
            for (int kForBucket = 0; kForBucket <= k; kForBucket++)
            {
                this.buckets[index] = kForBucket;
                this.distribute(k - kForBucket, index + 1, action, preCondition);
            }
        }
    }

    public static interface ActionPerBucketDistribution
    {
        public void action(int[] buckets);
    }

    public static interface Condition
    {
        public boolean accept(int n, int k, int index, int[] currentBuckets);
    }

    public static void main(String[] args)
    {
        final int N = 8;

        Buckets buckets = new Buckets(N);

        Condition preCondition = (int n, int k, int index, int[] currentBuckets) ->
        {
            boolean descending = (index < 2 || (currentBuckets[index - 2] >= currentBuckets[index - 1]));
            boolean smallerThanN = (index < 1 || currentBuckets[index - 1] < n);

            return descending && smallerThanN;
        };

        List<int[]> results = buckets.distribute(N, preCondition);

        System.out.println("Result: " + results.size());

        for (int[] result : results)
        {
            System.out.println(Arrays.toString(result));
        }
    }
}
