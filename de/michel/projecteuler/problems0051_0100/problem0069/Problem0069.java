package de.michel.projecteuler.problems0051_0100.problem0069;

import java.util.List;
import de.michel.projecteuler.util.PrimeGenerator;

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
        long totalPrimeTime = 0l;
        long totalPhiTime = 0l;

        double ratio, maxRatio = 0;
        int maxRatioN = 0;
        int coprime;

        PrimeGenerator pg = new PrimeGenerator();

        for (int n = 1; n <= N; n++)
        {
            long primeTime = System.currentTimeMillis();
            List<Long> facs = pg.getPrimeFactors(n);
            totalPrimeTime += (System.currentTimeMillis() - primeTime);

            long phiTime = System.currentTimeMillis();
            coprime = calculatePhi(n, facs);
            totalPhiTime += (System.currentTimeMillis() - phiTime);

            ratio = 1.0 * n / coprime;
            if (ratio > maxRatio)
            {
                maxRatio = ratio;
                maxRatioN = n;
            }
        }

        System.out.println("PrimeTime: " + totalPrimeTime);
        System.out.println("PhiTime:   " + totalPhiTime);

        System.out.println("Result: " + maxRatioN);
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
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
                phi *= (1.0 - 1.0 / fac);
                lastFac = fac;
            }
        }
        return (int) (phi + 0.5);
    }

}
