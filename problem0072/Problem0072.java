package problem0072;

import util.PrimeGenerator;

/**
 *
 * Consider the fraction, n/d, where n and d are positive integers. If n<d and HCF(n,d)=1, it is called a reduced proper fraction.<p>
 *
 * If we list the set of reduced proper fractions for d ≤ 8 in ascending order of size, we get:
 * <p>
 *
 * 1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
 * <p>
 *
 * It can be seen that there are 21 elements in this set.<p>
 *
 * How many elements would be contained in the set of reduced proper fractions for d ≤ 1,000,000?
 *
 * @author micmeyer
 */
public class Problem0072
{

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        PrimeGenerator pg = new PrimeGenerator();

        /*
         * Given a denominator d there are d-1 fractions smaller than 1
         * for that denominator, from 1/d to (d-1)/d.
         *
         * Now for each numerator n with 1 <= n <= d-1 every fraction
         * with gcd(n, d) > 1 can be reduced and with gcd(n, d) == 1
         * cannot be reduced.
         *
         * So the goal is to determine the number of n's with
         * gcd(n, d) == 1 for each d. And this number is exactly the
         * number of coprimes for d because n/d cannot be reduced if
         * n is a coprime of d.
         *
         * So that's all we're doing here: Add up the number of coprimes
         * of d for each d from 2 to 1.000.000.
         */
        long realFractions = 0;

        for (int denominator = 2; denominator <= 1000000; denominator++)
            realFractions += PrimeGenerator.calculatePhi(denominator, pg.getPrimeFactors(denominator));

        System.out.println("Result: " + realFractions);
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }
}
