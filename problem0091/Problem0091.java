package problem0091;

/**
 *
 *
 *
 * @author micmeyer
 */
public class Problem0091
{
    private static final int TARGET = 50;

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        int result = 0;

        for (int x1 = 0; x1 <= TARGET; x1++)
            for (int y1 = 0; y1 <= TARGET; y1++)
                for (int x2 = 0; x2 <= TARGET; x2++)
                    for (int y2 = 0; y2 <= TARGET; y2++)
                        if (checkIfRightAngle(x1, y1, x2, y2))
                            result++;

        System.out.println("Result: " + result / 2);

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static boolean checkIfRightAngle(int x1, int y1, int x2, int y2)
    {
        int squaredLen1 = x1 * x1 + y1 * y1;
        int squaredLen2 = x2 * x2 + y2 * y2;

        int dx = Math.abs(x1 - x2);
        int dy = Math.abs(y1 - y2);
        int squaredLen3 = dx * dx + dy * dy;

        if (squaredLen1 == 0 || squaredLen2 == 0 || squaredLen3 == 0)
            return false;

        if (squaredLen1 == squaredLen2 + squaredLen3)
            return true;

        if (squaredLen2 == squaredLen1 + squaredLen3)
            return true;

        return squaredLen3 == squaredLen1 + squaredLen2;
    }

}
