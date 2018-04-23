package problem0086;

/**
 *
 * Better see https://projecteuler.net/problem=86 for a picture.
 *
 * @author micmeyer
 */
public class Problem0086
{
    private static final int TARGET_M = 1000000;

    /**
     * ..................B...<br>
     * .......+---------+....<br>
     * ....../|......../|....<br>
     * ...../.|......./.|....<br>
     * ....+---------+..|h=3.<br>
     * ....|..|......|..|....<br>
     * ....|..+------|--+....<br>
     * ....|./.......|./.....<br>
     * ....|/........|/.d=5..<br>
     * ....+---------+.......<br>
     * ...A.....w=6..........<br>
     * <p>
     * If you want to get from A to B on a "direct line" (but while staying on the
     * sides of the cuboid) you can imagine to "fold down" one the sides in a certain
     * direction.
     * <p>
     * For example, fold down the back-side so that B lands on the floor. Now A and B
     * are on a 2-dimensional space, a rectangle, that has a width of 6 and a height
     * of 5+3=8. So the distance to travel will be:
     * <pre>
     * s = sqrt( w² + (h+d)² )
     * </pre>
     * If you look closely you can image two other "folding" possibilities to achieve
     * similar formulae, which are:
     * <pre>
     * s = sqrt( h² + (w+d)² )
     *
     * s = sqrt( d² + (h+w)² )
     * </pre>
     * Interestingly enough the actual shortest path will be the one where the both
     * variables INSIDE the braces are the smallest or, in other words, the largest of
     * the three is the one OUTSIDE the braces.
     * <p>
     * So in this example the shortest path would be:
     * <pre>
     * s = sqrt ( 6² + (3+5)² ) = sqrt( 36 + 64 ) = sqrt( 100 ) = 10
     * </pre>
     * (Sorry, no proof here for that claim...)
     * <p>
     * We could now brute force our way out by letting m = 1 and increment it until
     * we have found our goal. For our width, height and depth we use a, b and c with
     * the restriction of a &le; b &le; c (to sort out the rotations) and then we let:
     * <pre>
     * c = m
     * b = 1..c
     * a = 1..b
     * </pre>
     * For each combination we compute the formula above
     * <pre>
     * s = sqrt ( (a + b)² + c² )
     * </pre>
     * and check if it is integer. And once the amount of integers will exceed the target
     * we have found the wanted result (the m).
     * <p>
     * <p>
     * HOWEVER there is a more clever idea to solve this. This will (or is at least
     * tried to) be explained in {@link Problem0086#calcCountFor(int)}.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        int m = 0, count = 0;

        while (count <= TARGET_M)
            count += calcCountFor(m++);

        System.out.println("Result: " + (m - 1));

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    /**
     * Instead of iterating over every possible combination as explained in
     * {@link Problem0086#main(java.lang.String[])} we can actually calculate
     * the number of possibilities for any given c ('c' as in 'a, b and c').
     * Well, not quite explicitly, we still have a for-loop but only ONE
     * instead of TWO which makes this way faster.
     * <p>
     * Roughly explained:
     * If you have found, for example:
     * <pre>
     * s = sqrt ( (3+5)² + 6² ) = 10
     * </pre>
     * as a solution you can directly infer the following solutions, too:
     * <pre>
     * s = sqrt ( (2+6)² + 6² ) = 10
     *
     * s = sqrt ( (4+4)² + 6² ) = 10
     * </pre>
     * c=m=6 is set while a and b can be adjusted so that their sum remains the
     * same (and the whole formula also remains the same) but creates a
     * different cuboid.
     * <p>
     * That's what this method calculates, so calcCountFor(6) will result in 3.
     * <p>
     * More detailed explaination:
     * Let's take the formula and square both sides. This will result in the
     * traditional pythagorean formula (if you see 'a+b' as a unit):
     * <pre>
     * s² = (a+b)² + c²
     * </pre>
     * Now 'c' is what goes into the method. So we look for a viable number for
     * 'a+b' which is if and only if '(a+b)² + c²' is a square number.
     * <p>
     * Let's pretend we have found such a 'a+b'. In the example above, if c=6 we
     * will find 8 as a viable 'a+b' as '8²+6²' is a square number. Having found
     * that we could now add up the amound of possible combinations that sum up
     * to 8 like '1+7', '2+6', ... '7+1'.
     * <p>
     * BUT there are some restrictions:<br>
     * We still have a &le; b &le; c, so that must hold. This not only cancels
     * out all the combinations where a &gt; b (like '5+3' or '6+2') but also the
     * ones where b &gt; c or even a &gt; c (like '1+7' or '7+1').
     * <p>
     * Furthermore (for the same reason, just inferred), 'a+b' cannot be greater
     * than 2*c. If it was, at least one of a and b would have to be greater
     * than c. That's why we try '1..2*c' as our 'a+b'.
     * <p>
     * The very complicated thing to explain then happens inside the for-block
     * once we have found a viable 'a+b' ('x' in the method). After printing
     * out a couple of viable combinations it was possible to figure out some
     * kind of "formula". Generally the number of possibilites is just x/2
     * (or 'a+b'/2 if you will), rounded down. But once x becomes greater than
     * 'c+1' we have to subtract exactly this "overstepped amount" to compensate
     * the falsely counted cases where a or b is greater than c.
     *
     * @param c
     *
     * @return
     */
    private static int calcCountFor(int c)
    {
        int cSquare = c * c;
        int sum, over;
        int count = 0;

        /*
         * Look for a viable 'a+b' so that
         * (a+b)² + c² is a sqaure number.
         */
        for (int x = 1; x <= 2 * c; x++)
        {
            sum = x * x + cSquare;

            if (isSquareNumber(sum))
            {
                /*
                 * Generally x/2 is the amount
                 * of possibilities but...
                 */
                count += x / 2;

                /*
                 * ... if x is greater than c+1
                 * we take away the ones where
                 * a or b is greater than c.
                 */
                over = x - (c + 1);
                if (over > 0)
                    count -= over;
            }
        }

        return count;
    }

    private static boolean isSquareNumber(int n)
    {
        double sqrt = Math.sqrt(n);
        return sqrt == (int) sqrt;
    }
}
