package problem0070;

import java.util.List;
import util.PrimeGenerator;

/**
 *
 *
 *
 * @author micmeyer
 */
public class Problem0070
{
    private static final int N = 10000000;

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        PrimeGenerator pg = new PrimeGenerator();
        List<Long> primeFactors;
        int coprime;
        double ratioMin = 10.0;

        PhiDigits resultDigits = null;

        for (int n = 2; n < N; n++)
        {
            primeFactors = pg.getPrimeFactorsCached(n);
            coprime = calculatePhi(n, primeFactors);

            PhiDigits phiDigits = new PhiDigits(n, coprime);

            if (phiDigits.isAnagram() && phiDigits.ratio < ratioMin)
            {
                ratioMin = phiDigits.ratio;
                resultDigits = phiDigits;
            }
        }

        if (resultDigits != null)
            System.out.println("Result: " + resultDigits.n);
        else
            System.out.println("Result: -");
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

    private static class PhiDigits
    {
        long n;
        long phi;
        double ratio;

        byte[] nDigits;
        byte[] phiDigits;

        PhiDigits(long n, long phi)
        {
            this.n = n;
            this.phi = phi;
            this.ratio = 1.0 * n / phi;

            this.nDigits = createDigits(n);
            this.phiDigits = createDigits(phi);
        }

        public boolean isAnagram()
        {
//            if (this.nDigits.length != this.phiDigits.length)
//                return false;

            for (int t = 0; t < this.nDigits.length; t++)
                if (this.nDigits[t] != this.phiDigits[t])
                    return false;

            return true;
        }

        private static byte[] createDigits(long n)
        {
            byte[] digits = new byte[10];
            int d;

            while (n > 0)
            {
                d = (int) n % 10;
                digits[d]++;
                n /= 10;
            }

            return digits;
        }
    }

    private static void test()
    {
        PhiDigits d;

        d = new PhiDigits(123, 321);
        System.out.println(d.isAnagram());
        d = new PhiDigits(321, 123);
        System.out.println(d.isAnagram());
        d = new PhiDigits(12345, 32541);
        System.out.println(d.isAnagram());
        d = new PhiDigits(10001, 11000);
        System.out.println(d.isAnagram());
        d = new PhiDigits(789456, 698547);
        System.out.println(d.isAnagram());

        System.out.println("============");

        d = new PhiDigits(123, 3211);
        System.out.println(d.isAnagram());
        d = new PhiDigits(122, 123);
        System.out.println(d.isAnagram());
        d = new PhiDigits(12345, 11111);
        System.out.println(d.isAnagram());
        d = new PhiDigits(10001, 00011);
        System.out.println(d.isAnagram());
        d = new PhiDigits(69854, 698547);
        System.out.println(d.isAnagram());
        d = new PhiDigits(698547, 69854);
        System.out.println(d.isAnagram());
    }
}
