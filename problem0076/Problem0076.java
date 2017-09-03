package problem0076;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 *
 * @author micmeyer
 */
public class Problem0076
{
    private static final Map<Integer, Map<Integer, Integer>> MAP = new HashMap<>();

    private static final int N = 100;

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        for (int n = 3; n < N; n++)
            calcAndRemember(n);

        System.out.println("Result: " + calcAndRemember(N));
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static int calcAndRemember(int n)
    {
        int count = get(n, 1);

        Map<Integer, Integer> countPerFirstAddend = new HashMap<>();
        MAP.put(n, countPerFirstAddend);

        for (int firstAddend = 2; firstAddend < n; firstAddend++)
        {
            count += get(n, firstAddend);

            countPerFirstAddend.put(firstAddend, count);
        }

        return count;
    }

    private static int get(int n, int firstAddend)
    {
        int rest = n - firstAddend;

        if (rest == 1 || firstAddend == 1)
            return 1;

        if (firstAddend >= rest)
        {
            firstAddend = rest - 1;

            if (firstAddend == 1)
                return 2;

            return MAP.get(rest).get(firstAddend) + 1;
        }

        return MAP.get(rest).get(firstAddend);
    }
}
