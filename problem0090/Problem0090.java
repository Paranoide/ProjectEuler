package problem0090;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import util.Sets;

/**
 *
 * Each of the six faces on a cube has a different digit (0 to 9) written on it; the same is done to a second cube. By placing the two cubes side-by-side in
 * different positions we can form a variety of 2-digit numbers.
 * <p>
 * For example, the square number 64 could be formed:
 * <p>
 * [imagine an image with two cubes here, the left one displaying a 6 and the right one displaying a 4]
 * <p>
 * In fact, by carefully choosing the digits on both cubes it is possible to display all of the square numbers below one-hundred: 01, 04, 09, 16, 25, 36, 49,
 * 64, and 81.
 * <p>
 * For example, one way this can be achieved is by placing {0, 5, 6, 7, 8, 9} on one cube and {1, 2, 3, 4, 8, 9} on the other cube.
 * <p>
 * However, for this problem we shall allow the 6 or 9 to be turned upside-down so that an arrangement like {0, 5, 6, 7, 8, 9} and {1, 2, 3, 4, 6, 7} allows for
 * all nine square numbers to be displayed; otherwise it would be impossible to obtain 09.
 * <p>
 * In determining a distinct arrangement we are interested in the digits on each cube, not the order.
 * <p>
 * {1, 2, 3, 4, 5, 6} is equivalent to {3, 6, 4, 1, 2, 5}
 * {1, 2, 3, 4, 5, 6} is distinct from {1, 2, 3, 4, 5, 9}
 * <p>
 * But because we are allowing 6 and 9 to be reversed, the two distinct sets in the last example both represent the extended set {1, 2, 3, 4, 5, 6, 9} for the
 * purpose of forming 2-digit numbers.
 * <p>
 * How many distinct arrangements of the two cubes allow for all of the square numbers to be displayed?
 *
 * @author micmeyer
 */
public class Problem0090
{
    private static final List<Set<Integer>> ALL_POSSIBLE_SETS = new ArrayList<>();

    private static final List<Point> SQUARE_NUMBER_DIGIT_PAIRS = new ArrayList<>();

    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        determineAllSquareNumberDigitsPairs();
        determineAllPossibleSets();

        // Cross product (without duplicate combinations)
        int counter = 0;
        for (int t1 = 0; t1 < ALL_POSSIBLE_SETS.size(); t1++)
            for (int t2 = t1; t2 < ALL_POSSIBLE_SETS.size(); t2++)
                if (checkIsValidArrangement(ALL_POSSIBLE_SETS.get(t1), ALL_POSSIBLE_SETS.get(t2)))
                    counter++;

        System.out.println("Result: " + counter);

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static void determineAllSquareNumberDigitsPairs()
    {
        for (int t = 1; t <= 9; t++)
        {
            int square = t * t;
            int digit1 = square / 10;
            int digit2 = square % 10;
            SQUARE_NUMBER_DIGIT_PAIRS.add(new Point(digit1, digit2));
        }
    }

    /**
     * Create all the (10 choose 6) = 210 possible arrangement on one die.
     * As 6 and 9 can be turned upside down to represent each other all
     * those sets containing one of them will be extended by the other;
     * except they are already both contained, of course.
     */
    private static void determineAllPossibleSets()
    {
        List<Integer> allDigits = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Set<Integer>> findAllSubsetsOfLengthN = Sets.findAllSubsetsOfLengthN(allDigits, 6);

        for (Set<Integer> set : findAllSubsetsOfLengthN)
        {
            if (set.contains(6))
                set.add(9);
            else if (set.contains(9))
                set.add(6);

            ALL_POSSIBLE_SETS.add(set);
        }
    }

    /**
     * For each square number check if one digit is contained in one the
     * given sets and if the other digit is contained in the other set;
     * regardless which digit is contained in which set.
     * <p>
     * If for any square number this check fails the result is false,
     * otherwise true.
     *
     * @param set1
     * @param set2
     *
     * @return
     */
    private static boolean checkIsValidArrangement(Set<Integer> set1, Set<Integer> set2)
    {
        for (Point p : SQUARE_NUMBER_DIGIT_PAIRS)
        {
            if (set1.contains(p.x) && set2.contains(p.y))
                continue;

            if (set1.contains(p.y) && set2.contains(p.x))
                continue;

            return false;
        }

        return true;
    }

}
