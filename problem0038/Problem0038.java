package problem0038;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Take the number 192 and multiply it by each of 1, 2, and 3:<br/>
 * <pre>
 * 192 × 1 = 192
 * 192 × 2 = 384
 * 192 × 3 = 576
 * </pre>
 * By concatenating each product we get the 1 to 9 pandigital, 192384576. We
 * will call 192384576 the concatenated product of 192 and (1,2,3)<br/>
 * <br/>
 * The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4,
 * and 5, giving the pandigital, 918273645, which is the concatenated product of
 * 9 and (1,2,3,4,5).<br/>
 * <br/>
 * What is the largest 1 to 9 pandigital 9-digit number that can be formed as
 * the concatenated product of an integer with (1,2, ... , n) where n > 1?
 *
 * @author micmeyer
 */
public class Problem0038
{

    private static final List<Integer> DIGITS = new ArrayList<>();

    private static final List<Integer> HELPER = new ArrayList<>();

    private static String max = "000000000";

    static
    {
        for (int t = 1; t < 10; t++)
        {
            DIGITS.add(t);
        }
    }

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        for (int t = 0; t < 10000; t++)
        {
            String str = concatTheMultiplicatives(t);
            if (str.length() == 9)
            {
                if (str.compareTo(max) > 0)
                {
                    if (isPandigital(str))
                    {
                        max = str;
                    }
                }
            }
        }

        System.out.println("Max: " + max);

        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static boolean isPandigital(String n)
    {
        HELPER.addAll(DIGITS);
        int digit;
        for (int index = 0; index < 9; index++)
        {
            digit = n.charAt(index) - '0';
            if (HELPER.contains(digit))
            {
                HELPER.remove((Integer) digit);
            }
            else
            {
                HELPER.clear();
                return false;
            }
        }

        return true;
    }

    private static String concatTheMultiplicatives(int n)
    {
        StringBuilder sb = new StringBuilder();
        int mult = 1;
        while (sb.length() < 9)
        {
            sb.append(mult * n);
            mult++;
        }
        return sb.toString();
    }

}
