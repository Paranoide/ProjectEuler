package problem0074;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO Some description here...
 *
 * @author Michel Meyer <micmeyer@uos.de>
 */
public class Buckets
{
    private final int[] buckets;

    private List<int[]> allPossibleBuckets;

    public Buckets(int n)
    {
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
        this.allPossibleBuckets = new ArrayList<>();

        ActionPerBucketDistribution action = (int[] buckets1) ->
        {
            int[] bucketsCopy = new int[buckets1.length];
            System.arraycopy(buckets1, 0, bucketsCopy, 0, buckets1.length);
            allPossibleBuckets.add(bucketsCopy);
        };

        this.distribute(k, action);

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
        this.distribute(k, 0, action);
    }

    private void distribute(int k, int index, ActionPerBucketDistribution action)
    {
        if (index == this.buckets.length - 1)
        {
            this.buckets[index] = k;
            action.action(this.buckets);
        }
        else
        {
            for (int kForBucket = 0; kForBucket <= k; kForBucket++)
            {
                this.buckets[index] = kForBucket;
                this.distribute(k - kForBucket, index + 1, action);
            }
        }
    }

    public static interface ActionPerBucketDistribution
    {
        public void action(int[] buckets);
    }
}
