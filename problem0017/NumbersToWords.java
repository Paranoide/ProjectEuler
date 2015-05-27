package problem0017;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author micmeyer
 */
public class NumbersToWords
{

    private static final Map<Integer, String> map = new HashMap<>();

    static
    {
        map.put(0, "zero");
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        map.put(7, "seven");
        map.put(8, "eight");
        map.put(9, "nine");
        map.put(10, "ten");
        map.put(11, "eleven");
        map.put(12, "twelve");
        map.put(13, "thirteen");
        map.put(14, "fourteen");
        map.put(15, "fifteen");
        map.put(16, "sixteen");
        map.put(17, "seventeen");
        map.put(18, "eighteen");
        map.put(19, "nineteen");
        map.put(20, "twenty");
        map.put(30, "thirty");
        map.put(40, "forty");
        map.put(50, "fifty");
        map.put(60, "sixty");
        map.put(70, "seventy");
        map.put(80, "eighty");
        map.put(90, "ninety");
        map.put(90, "ninety");
        map.put(100, "hundred");
        map.put(1000, "thousand");
    }

    private static final String SEPERATOR = " ";
    private static final String HYPHEN = "-";

    public static String numberToWords(int n)
    {
        if (n == 0)
        {
            return map.get(0);
        }
        else
        {
            return numberToWordsWithoutZero(n);
        }
    }

    private static String numberToWordsWithoutZero(int n)
    {
        StringBuilder sb = new StringBuilder();

        // thousands
        if (n >= 1000)
        {
            // 23567
            // ts = 23
            int ts = n / 1000;
            String tsStr = numberToWordsWithoutZero(ts);
            sb.append(tsStr);
            sb.append(SEPERATOR);
            sb.append(map.get(1000));
            sb.append(SEPERATOR);

            n = n % 1000;
        }

        // hundreds
        if (n >= 100)
        {
            // 567

            int hs = n / 100;
            String hsStr = numberToWordsWithoutZero(hs);
            sb.append(hsStr);
            sb.append(SEPERATOR);
            sb.append(map.get(100));
            sb.append(SEPERATOR);

            n = n % 100;
            if (n > 0)
            {
                sb.append("and");
                sb.append(SEPERATOR);
            }
        }

        // two-digits
        if (n >= 20)
        {
            // 67
            // d1 = 6
            // d2 = 7
            int d1 = (n / 10);
            String d1Str = map.get(10 * d1);
            sb.append(d1Str);
            
            n = n - 10 * d1;
            if (n > 0)
            {
                sb.append(HYPHEN);
            }

        }

        // one-digits and specials
        if (n > 0)
        {
            String dStr = map.get(n);
            sb.append(dStr);
        }

        return sb.toString();
    }

    public static void main(String[] args)
    {

        int[] test =
        {
            0,
            12,
            19,
            20,
            21,
            25,
            33,
            75,
            100,
            105,
            111,
            123,
            543,
            699,
            702,
            999,
            1000
        };

        for (int t = 0; t < test.length; t++)
        {
            System.out.println(test[t] + ":" + numberToWords(test[t]));
        }
    }
}
