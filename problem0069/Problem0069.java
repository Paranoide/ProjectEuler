package problem0069;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import util.Divisors;

/**
 *
 * 2,3,4,5,6,7,8,9,10,12,14,15,16,17,18,20,21,22,24,25,26,27,28
 * 32,33,34,35,36,38,39,40,42,44,45,46,47,48,49,50,51,52,54,55,56,57,58
 * @author micmeyer
 */
public class Problem0069
{
    private static final int N = 1000;
    private static int[] coprimes;
    
    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();
        
        coprimes = new int[N];
        for (int t = 0; t < N; t++)
        {
            coprimes[t] = -1;
        }
        
        int tmpN, c;
        for (int n = 2; n <= N; n++)
        {
            if (n % 1000 == 0)
            {
                System.out.println(n);
            }
            if (phi(n) < 0)
            {
                List<Integer> co = calculateCoprimes(n);
                Integer coprimeCount = co.size() + 1;
                coprimes[n-1] = coprimeCount;

                tmpN = 0;
                Iterator<Integer> it = co.iterator();
                boolean done = false;
                
                
                
                while (it.hasNext() && !done)
                {
                    c = it.next();
                    tmpN = c*n;
                    if (tmpN-1 < N)
                    {
                        coprimes[tmpN-1] = phi(c)*phi(n);
                    }
                    else
                    {
                        done = true;
                    }
                }
                
            }
        }
        
        double max = 0;
        int maxN = 0;
        for (int t = 0; t < N; t++)
        {
            double ratio = 1.0*(t+1)/coprimes[t];
            if (ratio > max)
            {
                max = ratio;
                maxN = t+1;
            }
            
            System.out.printf("%3d: %d (%f)\n", t+1, coprimes[t], ratio);
        }
        System.out.printf("Max: %f at %d\n", max, maxN);
        
        System.out.println(calculateCoprimes(30));
        System.out.println(calculateCoprimes(60));
        System.out.println(calculateCoprimes(90));
        System.out.println(calculateCoprimes(270));
        System.out.println(calculateCoprimes(210));
        
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis()-time));
    }
    
    private static int phi(int n)
    {
        return coprimes[n-1];
    }
    
    private static List<Integer> calculateCoprimes(int i)
    {
        List<Integer> co = new LinkedList<>();
        
        for (int t = 2; t < i; t++)
        {
            if (Divisors.gcd(t, i) == 1)
            {
                co.add(t);
            }
        }
        
        return co;
    }
    
}
