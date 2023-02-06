package de.michel.projecteuler.problem0039;

/**
 *
 * If p is the perimeter of a right angle triangle with integral length sides,
 * {a,b,c}, there are exactly three solutions for p = 120.
 * <pre>
 * {20,48,52}, {24,45,51}, {30,40,50}
 * </pre>
 * For which value of p &le; 1000, is the number of solutions maximised?
 *
 * @author micmeyer
 */
public class Problem0039
{

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        int max = 0;
        int theP = 0;
        int c;
        for (int p = 0; p < 1000; p++)
        {
            c = getPossCount(p);
            if (c > max)
            {
                max = c;
                theP = p;
            }
        }

        System.out.printf("At p == %d there are %d possibilities.\n", theP, max);

        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static int getPossCount(int p)
    {
        int count = 0;
        for (int a = 1; a < p / 3 + 1; a++)
        {
            for (int b = a; b < (p - a) / 2 + 1; b++)
            {
                int c = p - (a + b);

                if ((a * a + b * b) == c * c)
                {
                    count++;
                }
            }
        }
        return count;
    }

}
