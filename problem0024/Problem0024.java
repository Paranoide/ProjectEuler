package problem0024;

/**
 *
 * A permutation is an ordered arrangement of objects. For example, 3124 is one
 * possible permutation of the digits 1, 2, 3 and 4. If all of the permutations
 * are listed numerically or alphabetically, we call it lexicographic order. The
 * lexicographic permutations of 0, 1 and 2 are:
 * <pre>
 * 012 021 102 120 201 210
 * </pre>
 * What is the millionth lexicographic permutation of the digits 0, 1, 2,
 * 3, 4, 5, 6, 7, 8 and 9?
 *
 * @author micmeyer
 */
public class Problem0024
{

    private static long permCounter = 0;

    public static void main(String[] args)
    {
        int[] perm =
        {
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        };

        permutate(perm);
    }

    private static void permutate(int[] input)
    {
        permutate(input, 0, new int[input.length]);
    }

    private static void permutate(int[] input, int depth, int[] currPerm)
    {
        if (depth == input.length)
        {
            permCounter++;
            if (permCounter == 1000000)
            {
                printPermutation(currPerm);
                System.exit(1);
            }
        }
        else
        {
            for (int t = 0; t < input.length; t++)
            {
                int i = input[t];
                if (i >= 0)
                {
                    currPerm[depth] = i;
                    input[t] = -1;
                    permutate(input, depth + 1, currPerm);
                    input[t] = i;
                }
            }
        }
    }
    
    private static void printPermutation(int[] perm)
    {
        for (int i: perm)
        {
            System.out.print(i);
        }
        System.out.println();
    }

}
