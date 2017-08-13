package problem0073;

import util.Divisors;

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
 * It can be seen that there are 3 fractions between 1/3 and 1/2
 * .<p>
 *
 * How many fractions lie between 1/3 and 1/2 in the sorted set of reduced proper fractions for d ≤ 12,000?
 *
 * @author micmeyer
 */
public class Problem0073
{

    /**
     * The idea is to determine for each denominator d with 2 <= d <= 12000
     * the first and last numerator in the desired range (between 1/3 and 1/2).
     * For example:
     *
     * d = 10
     *
     * First numerator: numStart = ceil(10 / 3) = 4
     * Last numerator: numEnd = floor(10 / 2) = 5
     *
     * So 4/10 and 5/10 are the only values between 1/3 and 1/2 for d = 10. We
     * then check the gcd of numerator and denominator and if it is 1 then it's
     * a non-reduceable fraction.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        /*
         * We substract 2 because the algorithm also gives us
         * 1/3 and 1/2 which are excluded in the problem description.
         */
        int result = -2;

        for (int denominator = 2; denominator <= 12000; denominator++)
        {
            int numStart = (int) Math.ceil(denominator / 3.0);

            int numEnd = denominator / 2;

            for (int numerator = numStart; numerator <= numEnd; numerator++)
            {
                if (Divisors.gcd(numerator, denominator) == 1)
                    result++;
            }
        }

        System.out.println("Result: " + result);
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

}
