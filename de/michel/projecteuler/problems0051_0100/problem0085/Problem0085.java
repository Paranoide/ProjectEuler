package de.michel.projecteuler.problems0051_0100.problem0085;

/**
 *
 * By counting carefully it can be seen that a rectangular grid measuring 3 by 2 contains eighteen rectangles:
 * <p>
 * (See https://projecteuler.net/problem=85 for a picture)
 * <p>
 * Although there exists no rectangular grid that contains exactly two million rectangles, find the area of the grid with the nearest solution.
 *
 * @author micmeyer
 */
public class Problem0085
{
    private static final int TARGET_RECT_COUNT = 2000000;

    private static int width;
    private static int height = 1;

    private static int bestResult = Integer.MAX_VALUE;
    private static int bestResultGridSize = 0;

    /**
     * We kind of brute force for every possible grid size
     * but in a smart way as we can approximately calculate
     * where to look.
     * <p>
     * We start with a height of 1. Now we don't need to start
     * with width == 1 until the rectangle count reaches our
     * target (say it's 2.000.000) but we can guess a width
     * that will result in approximately 2.000.000 rectangles.
     * <p>
     * In fact, our calculation makes it so that the actual
     * rectangle count is ALWAYS greater than the target. So
     * from then on we decrease the width one by one until the
     * rectangle count is below our target (**). At this point we
     * found the minimum for the current height - either this
     * rectangle count or the one before (at width + 1).
     * <p>
     * Then we can increase the height by one and repeat. We
     * only have to memorize the overall minimum (bestResult)
     * and the grid size at that minimum (bestResultGridSize)
     * as this will be the answer to the problem.
     * <p>
     * Note (**): When executing for a target of 2.000.000 the
     * amount of width-decreasing iterations was never more than
     * 3, so the rectangle cound width startWidth was always
     * very close to the target.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        int currRectCount, currDiff;

        do
        {
            width = calcStartWidth(height);

            do
            {
                currRectCount = countContainingRectangles(width, height);

                currDiff = diffToTarget(currRectCount);

                if (currDiff < bestResult)
                {
                    bestResult = currDiff;
                    bestResultGridSize = width * height;
                }

                width--;
            }
            while (currRectCount > TARGET_RECT_COUNT);

            height++;
        }
        while (width >= height);

        System.out.println("Result: " + bestResultGridSize);

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    /**
     * To calculate the amount of rectangles that will be possible we need to take every
     * possible rectangle size that will fit into our grid and calculate the amount of
     * possibilities to place that one into the grid - and finally sum up all these amounts.
     * <p>
     * The "easy" way is to make a loop that lets
     * <ul>
     * <li>innerRectangleWidth go from 1 to gridWidth and</li>
     * <li>innerRectangleHeight go from 1 to gridHeight </li>
     * </ul>
     * and then we can derive how many times it will fit from left to right and how many
     * times it will fit from top to bottom. Mutiply both numbers to get the amount of
     * ways this rectangle will fit into the grid.
     * <p>
     * The number of times to fit from left to right (and from top to bottom) are actually:
     * <pre>
     * leftToRightCount = gridWidth - innerRectangleWidth + 1
     * topToBottomCount = gridHeight - innerRectangleHeight + 1
     * </pre>
     * Example: innerRectWidth == 5, gridWidth = 7, it will fit 3 times:
     * <pre>
     * X-X-X-X-X-6-7
     *
     * 1-X-X-X-X-X-7
     *
     * 1-2-X-X-X-X-X
     * </pre>
     * (height goes analogously)
     * <p>
     * So in the end we can see we just multiply every innerRectangleWidth with every
     * innerRectangleHeight as leftToRightCount will assume the EXACT same numbers as
     * innerRectangleWidth (and leftToRightCount will assume the exact same numbers as
     * innerRectangleHeight).
     * <p>
     * That lets us simplify and use innerRectangleWidth and innerRectangleHeight
     * directly instead of calculating leftToRightCount and leftToRightCount. We now have
     * the following form:
     * <pre>
     * count = sum_{a=1 to w} sum_{b=1 to h} ( a*b )
     * </pre>
     * where w is the gridWidth and h is the gridHeight. Taking the Gaussian formula
     * <pre>
     * sum_{k=1 to n} = (n² + n) / 2
     * </pre>
     * we can do some maths and end up with:
     * <pre>
     * count = sum_{a=1 to w} sum_{b=1 to h} ( a*b )
     *
     * count = sum_{a=1 to w} ( a * sum_{b=1 to h} ( b ) )
     *
     * count = sum_{a=1 to w} ( a * (h² + h) / 2 )
     *
     * count = ( (h² + h) / 2 ) * sum_{a=1 to w} ( a )
     *
     * count = ( (h² + h) / 2 ) * ( (w² + w) / 2 )
     *
     * count = (w² + w) * (h² + h) / 4
     * </pre>
     * which is exactly what this method is calculating.
     *
     * @param gridWidth  The width of the current grid
     * @param gridHeight The height of the curent grid
     *
     * @return The number of times any rectangle of every integer width and
     *         height will fit into the grid speicified by gridWidth and gridHeight
     */
    private static int countContainingRectangles(int gridWidth, int gridHeight)
    {
        return (gridWidth * gridWidth + gridWidth) * (gridHeight * gridHeight + gridHeight) / 4;
    }

    /**
     * This method calculated an approximated gridWidth for the given gridHeight so
     * that its containing rectangle count will be very close to but also always
     * greater than the target rectangle count.
     * <p>
     * As a base it uses the same formula as {@link Problem0085#countContainingRectangles(int, int)}
     * does.
     * <p>
     * It basically solves the following equation for w:
     * <pre>
     * (w² + w) * (h² + h) / 4 = TARGET
     * </pre>
     * where h is gridHeight. The exact solution is
     * <pre>
     * w = sqrt( 4*TARGET / (h² + h) + 0.25 ) - 0.5
     * </pre>
     * but we can use an approximated solution by taking away the "small" numbers 0.25
     * and 0.5 and end up with
     * <pre>
     * w =~ sqrt( 4*TARGET / (h² + h) )
     * </pre>
     * The takeaway of the 0.25 inside the sqrt is much smaller than the addition of 0.5
     * outside, so the approximation is ensured to be ALWAYS greater than the exact
     * solution (**).
     * <p>
     * Note (**): At least for h &gt; 0, for h == 0 the approximation will be equal to the
     * exact value and for h &lt; 0 we just don't care as it is always h &gt; 0 in this case!
     *
     * @param gridHeight
     *
     * @return
     */
    private static int calcStartWidth(int gridHeight)
    {
        return (int) Math.ceil(Math.sqrt(TARGET_RECT_COUNT / (double) (gridHeight * gridHeight + gridHeight) * 4));
    }

    private static int diffToTarget(int n)
    {
        return Math.abs(TARGET_RECT_COUNT - n);
    }
}
