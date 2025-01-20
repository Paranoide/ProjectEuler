package de.michel.projecteuler.problems0051_0100.problem0071;

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
 * It can be seen that 2/5 is the fraction immediately to the left of 3/7
 * .<p>
 *
 * By listing the set of reduced proper fractions for d ≤ 1,000,000 in ascending order of size, find the numerator of the fraction immediately to the left of
 * 3/7.
 *
 * @author micmeyer
 */
public class Problem0071
{
    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        double fractionSmallerThanThreeSeventh;

        double clostestToThreeSeventh = 0.0;
        int result = 0;

        for (int denominator = 1; denominator <= 1000000; denominator++)
        {
            if (denominator % 7 == 0)
                continue;

            /*
             *
             * In short: For each denominator there is excatly one fraction smaller than but closest to 3/7.
             * Given this denominator and the target fraction 3/7 we can calculate said fraction. For all
             * denominators we then look for the highest (i.e.) closest fraction and remember the numerator.
             *
             * Long story:
             *
             * x / den = 3 / 7
             *
             * <=> x = 3*den / 7
             *
             * Now x is the value you need as the numerator to match exactly 3/7 for this denominator.
             * As is won't be a whole number (see note below) we round it down which gives us a new x.
             * After that x / den is the number smaller than but closest to 3/7 (i.e. the number "before"
             * 3/7) for the particular denominator.
             *
             * The all-in-one formula then is: f = floor(3*den / 7) / den
             *
             * We iterate through all denominators from 1 to 1.000.000 and find the highest number of
             * f in the given formula which will be the closest to but smaller than 3/7 of all fractions
             * that are taken into account by the problem description.
             *
             * Note: x can't be a whole number because this can only be the case when the denominator is
             * a multiple of 7 but we are excluding those.
             *
             */
            fractionSmallerThanThreeSeventh = Math.floor(3.0 * denominator / 7.0) / denominator;

            if (fractionSmallerThanThreeSeventh > clostestToThreeSeventh)
            {
                clostestToThreeSeventh = fractionSmallerThanThreeSeventh;
                result = (int) Math.floor(3.0 * denominator / 7.0);
            }
        }

        System.out.println("Result: " + result);
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

}
