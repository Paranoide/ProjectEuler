package problem0069;

import java.util.List;
import util.PrimeGenerator;

/**
 *
 * 
 * @author micmeyer
 */
public class Problem0069
{
    private static final int N = 1000000;
    
    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();
        
        double ratio, maxRatio = 0;
        int maxRatioN = 0;
        int coprime;
        for (int n = 1; n <= N; n++)
        {
            List<Long> facs = PrimeGenerator.getPrimeFactors(n);
            coprime = calculatePhi(n, facs);
            ratio = 1.0*n/coprime;
            if (ratio > maxRatio)
            {
                maxRatio = ratio;
                maxRatioN = n;
            }
        }
        
        System.out.println("Result: " + maxRatioN);
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis()-time));
    }
    
    private static int calculatePhi(int n, List<Long> primeFactors)
    {
        long lastFac = 0, fac;
        double phi = n;
        for (int t = 0; t < primeFactors.size(); t++)
        {
            fac = primeFactors.get(t);
            if (fac != lastFac)
            {
                phi *= (1.0 - 1.0/fac);
                lastFac = fac;
            }
        }
        return (int)(phi + 0.5);
    }
    
}
