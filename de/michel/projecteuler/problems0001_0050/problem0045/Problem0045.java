package de.michel.projecteuler.problems0001_0050.problem0045;

/**
 *
 * Triangle, pentagonal, and hexagonal numbers are generated by the following
 * formulae:
 * <pre>
 * Triangle:	T_n=n(n+1)/2	1, 3, 6, 10, 15, ...
 * Pentagonal:	P_n=n(3n−1)/2	1, 5, 12, 22, 35, ...
 * Hexagonal:	H_n=n(2n−1)	1, 6, 15, 28, 45, ...
 * </pre>
 * It can be verified that T_285 = P_165 = H_143 = 40755.<br/>
 * <br/>
 * Find the next triangle number that is also pentagonal and hexagonal.
 *
 * @author micmeyer
 */
public class Problem0045
{

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        boolean done = false;
        int n = 144;
        long hex;
        while (!done)
        {
            hex = getHexagonal(n);
            if (isTriangle(hex) && isPentagonal(hex))
            {
                System.out.println("Triangle: " + hex);
                done = true;
            }
            n++;
        }

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static long getHexagonal(int n)
    {
        return n * (2 * n - 1);
    }

    private static boolean isTriangle(long n)
    {
        double t = (Math.sqrt(8 * n + 1) - 1) / 2;
        return t == (int) t;
    }

    private static boolean isPentagonal(long n)
    {
        double p = (Math.sqrt(24 * n + 1) + 1) / 6;
        return p == (int) p;
    }

}
