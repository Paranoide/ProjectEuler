package de.michel.projecteuler.problem0094;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;

/**
 * @author micmeyer
 */
public class Problem0094
{
    private static final long TARGET = 1_000_000_000L;

    public static void main(String[] args)
    {
        executeProblem0094();
    }

    private static void executeProblem0094()
    {
        long time = System.currentTimeMillis();

        long result = 0;

        long maxK = TARGET / 3;

        for (long n = 2; n <= maxK; n++)
        {
            long check1 = (3 * n + 1) * (n - 1);
            long check2 = (3 * n - 1) * (n + 1);

            if (isSquareNumber(check1))
            {
                System.out.println("n+1 => " + n + " => (3*n+1)*(n-1) = " + check1 + " => Area = " + calcArea(n, n + 1));
                result += 3 * n + 1;
            }
            if (isSquareNumber(check2))
            {
                System.out.println("n-1 => " + n + " => (3*n-1)*(n+1) = " + check2 + " => Area = " + calcArea(n, n - 1));
                result += 3 * n - 1;
            }
        }

        System.out.println("Result: " + result);
        System.out.println("Time:   " + (System.currentTimeMillis() - time) + " ms.");
    }


    private static boolean isSquareNumber(long l)
    {
        long roundedSqrt = round(sqrt(l));
        long squaredRoundedSqrt = roundedSqrt * roundedSqrt;

        return squaredRoundedSqrt == l;
    }

    private static double calcArea(double a, double c)
    {
        return c / 4 * sqrt(4 * a * a - c * c);
    }
}
