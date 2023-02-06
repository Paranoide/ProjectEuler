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

    public static <T> List<List<T>> createPermutations(Collection<T> elements)
    {
        List<List<T>> allPerms = new ArrayList<>();

        createPermutationsRec(new ArrayList<>(elements), elements.size(), allPerms);

        return allPerms;
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

    private static <T> void createPermutationsRec(ArrayList<T> elements, int n, List<List<T>> allPerms)
    {
        if (n == 1)
        {
            allPerms.add(new ArrayList<>(elements));
            return;
        }

        for (int t = 0; t < n - 1; t++)
        {
            createPermutationsRec(elements, n - 1, allPerms);
            if (n % 2 == 0)
                swap(elements, t, n - 1);
            else
                swap(elements, 0, n - 1);
        }

        createPermutationsRec(elements, n - 1, allPerms);
    }

    private static <T> void swap(ArrayList<T> elements, int index1, int index2)
    {
        T elem = elements.get(index1);
        elements.set(index1, elements.get(index2));
        elements.set(index2, elem);
    }

    public static void main(String[] args)
    {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        List<List<Integer>> perms = createPermutations(list);

        for (List<Integer> perm : perms)
            System.out.println(perm);
        System.out.println(perms.size());
    }
}
