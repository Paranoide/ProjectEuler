package problem0015;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author micmeyer
 */
public class Problem0015
{

    public static Map<Integer, Long> gaussMap = new HashMap<>();
    public static Map<String, Long> sumMap = new HashMap<>();

    public static void main(String[] args)
    {

        final int N = 20;
        long sum = kSumsOver1ToN(N - 1, N + 1);

        System.out.println(sum);
    }

    /*
    
     1 + (n+1) + sum(1 to n + 1)
    
     */
    private static long kSumsOver1ToN(int k, int n)
    {
        if (k == 0)
        {
            return 1;
        }

        if (k == 1)
        {
            return smallGauss(n);
        }
        else
        {
            String key = "" + k + n;
            if (sumMap.containsKey(key))
            {
                return sumMap.get(key);
            }
            else
            {
                long sum = 0;
                for (int t = 1; t <= n; t++)
                {
                    sum += kSumsOver1ToN(k - 1, t);
                }
                sumMap.put(key, sum);
                
                return sum;
            }
        }
    }

    public static long smallGauss(int n)
    {
        long result;
        if (gaussMap.containsKey(n))
        {
            result = gaussMap.get(n);
        }
        else
        {
            result = n * (n + 1) / 2;
        }
        return result;
    }
}
