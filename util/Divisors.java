package util;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author micmeyer
 */
public class Divisors
{

    public static List<Long> getAllProperDivisors(long n)
    {
        List<Long> list = getAllDivisors(n);
        list.remove(n);
        return list;
    }
    
    public static List<Long> getAllDivisors(long n)
    {
        List<Long> divs = new LinkedList<>();
        long sqrt = (long)Math.sqrt(n);
        
        for (long t = 1; t <= sqrt; t++)
        {
            if (n % t == 0)
            {
                divs.add(t);
            }
        }
        
        int t = 0;
        long div;
        int size = divs.size() - 1;
        for ( ; t < size; t++)
        {
            div = divs.get(t);
            divs.add(size+1, n / div);
        }
        
        div = divs.get(t);
        if (n / div != div)
        {
            divs.add(size+1, n / div);
        }
        
        
        return divs;
    }

}
