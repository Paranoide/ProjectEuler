package de.michel.projecteuler.problem0064;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 * @author micmeyer
 */
public class Problem0064
{

    private static List<Integer> ns = new ArrayList<>();
    private static List<Integer> ds = new ArrayList<>();

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        int count = 0;
        
        for (int x = 2; x <= 10000; x++)
        {
            if (isIrrational(x))
            {
                if (hasOddPeriod(x))
                {
                    count++;
                }
            }
            ns.clear();
            ds.clear();
        }

        System.out.println("Result: " + count);
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }
    
    private static boolean isIrrational(int n)
    {
        double sqrt = Math.sqrt(n);
        return sqrt != (int)sqrt;
    }

    private static boolean hasOddPeriod(int x)
    {
        int sqrtInt = (int) Math.sqrt(x);
        int n = 0;
        int d = x;
        int g;
        int index;
        
        while (true)
        {
            d = (x - n * n) / d;
            g = (sqrtInt - n) / d;
            n = -n - g * d;
            index = addIfNotPresent(n, d);
            if (index >= 0)
            {
                return (ns.size() - index) % 2 == 1;
            }
        }
    }
    
    private static int addIfNotPresent(int n, int d)
    {
        int index = -1;
        for (int t = 0; t < ns.size() && index < 0; t++)
        {
            if (ns.get(t) == n && ds.get(t) == d)
            {
                index = t;
            }
        }
        if (index < 0)
        {
            ns.add(n);
            ds.add(d);
        }
        return index;
    }

}
