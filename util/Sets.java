package util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Michel Meyer <micmeyer@uos.de>
 */
public class Sets
{
    public static <T> List<Set<T>> findAllSubsetsOfLengthN(Collection<T> elements, int n)
    {
        int size = elements.size();

        if (size < n)
            return new ArrayList<>();

        List<Set<T>> allSubsets = new ArrayList<>();
        ArrayList<T> elementsAsArrayList = new ArrayList<>(elements);

        findAllSubsetsOfLengthNRec(elementsAsArrayList, n, allSubsets, new int[n], 1, 0, size);

        return allSubsets;
    }

    private static <T> void findAllSubsetsOfLengthNRec(ArrayList<T> elements, int n, List<Set<T>> subsets,
            int[] indices, int depth, int firstIndex, int size)
    {
        if (depth > n)
        {
            Set<T> subset = new HashSet<>();
            for (int index : indices)
                subset.add(elements.get(index));
            subsets.add(subset);

            return;
        }

        int lastIndex = size - n + depth;

        for (int t = firstIndex; t < lastIndex; t++)
        {
            indices[depth - 1] = t;
            findAllSubsetsOfLengthNRec(elements, n, subsets, indices, depth + 1, t + 1, size);
        }
    }
}
