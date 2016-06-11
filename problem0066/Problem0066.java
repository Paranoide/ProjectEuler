package problem0066;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Consider quadratic Diophantine equations of the form:
 * <pre>
 * x<sup>2</sup> – Dy<sup>2</sup> = 1
 * </pre>
 * For example, when D=13, the minimal solution in x is 6492 – 13×1802 = 1.<br/>
 * <br/>
 * It can be assumed that there are no solutions in positive integers when D is
 * square.<br/>
 * <br/>
 * By finding minimal solutions in x for D = {2, 3, 5, 6, 7}, we obtain the
 * following:
 * <pre>
 * 3<sup>2</sup> – 2×2<sup>2</sup> = 1
 * 2<sup>2</sup> – 3×1<sup>2</sup> = 1
 * <b>9</b><sup>2</sup> – 5×4<sup>2</sup> = 1
 * 5<sup>2</sup> – 6×2<sup>2</sup> = 1
 * 8<sup>2</sup> – 7×3<sup>2</sup> = 1
 * </pre>
 * Hence, by considering minimal solutions in x for D ≤ 7, the largest x is
 * obtained when D=5.<br/>
 * <br/>
 * Find the value of D ≤ 1000 in minimal solutions of x for which the largest
 * value of x is obtained.
 *
 * @author micmeyer
 */
public class Problem0066
{

    private static final List<Integer> ns = new ArrayList<>();
    private static final List<Integer> ds = new ArrayList<>();
    private static final List<Integer> seqList = new ArrayList<>();

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        BigInteger max = BigInteger.ZERO;
        int minD = -1;
        BigInteger currSol;
        BigInteger bigD;
        
        for (int D = 2; D <= 1000; D++)
        {
            if (isIrrational(D))
            {
                bigD = BigInteger.valueOf(D);
                currSol = findMinimalSolution(bigD);
                System.out.println(D + " : " + currSol);
                if (currSol.compareTo(max) > 0)
                {
                    max = currSol;
                    minD = D;
                }
            }
        }
        
        System.out.println("Min: " + max);
        System.out.println("Min: " + minD);
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static boolean isIrrational(int n)
    {
        double sqrt = Math.sqrt(n);
        return sqrt != (int) sqrt;
    }

    private static BigInteger findMinimalSolution(BigInteger x)
    {
        boolean done = false;
        int n = 2;
        Fraction f = Fraction.ZERO;
        while (!done)
        {
            f = getPartialContinuedFraction(x.intValue(), n);
            if (diophantine(f.num, f.den, x))
            {
                done = true;
            }
            else
            {
                n++;
            }
        }
        return f.num;
    }

    private static boolean diophantine(BigInteger x, BigInteger y, BigInteger D)
    {
        return x.multiply(x).subtract(y.multiply(y).multiply(D)).equals(BigInteger.ONE);
    }

    private static Fraction getPartialContinuedFraction(int x, int n)
    {
        int[] seq = calculateSequence(x);
        Fraction f = new Fraction(1, getNthFractionAddend(n, seq));

        n--;
        while (n > 1)
        {
            f.add(getNthFractionAddend(n, seq));
            f.reciproc();
            n--;
        }

        f.add(getNthFractionAddend(n, seq));

        return f;
    }

    private static int[] calculateSequence(int x)
    {
        int sqrtInt = (int) Math.sqrt(x);
        int n = 0;
        int d = x;
        int g;
        int index;
        boolean done = false;

        while (!done)
        {
            d = (x - n * n) / d;
            g = (sqrtInt - n) / d;
            seqList.add(g);
            n = -n - g * d;
            index = addIfNotPresent(n, d);
            if (index >= 0)
            {
                done = true;
            }
        }

        int[] seq = new int[seqList.size()];
        for (int t = 0; t < seq.length; t++)
        {
            seq[t] = seqList.get(t);
        }

        ns.clear();
        ds.clear();
        seqList.clear();

        return seq;
    }

    private static int addIfNotPresent(int n, int d)
    {
        int index = -1;
        for (int t = 0; t < ns.size() && index < 0; t++)
        {
            if (ns.get(t) == n && ds.get(t) == d)
            {
                index = t;
            }
        }
        if (index < 0)
        {
            ns.add(n);
            ds.add(d);
        }
        return index;
    }

    private static int getNthFractionAddend(int n, int[] sequence)
    {
        if (n == 1)
        {
            return sequence[0];
        }
        else
        {
            return sequence[((n - 2) % (sequence.length - 1)) + 1];
        }
    }

    private static class Fraction
    {

        public static final Fraction ZERO = new Fraction(0, 1);
        BigInteger num;
        BigInteger den;

        public Fraction(long n, long d)
        {
            this(BigInteger.valueOf(n), BigInteger.valueOf(d));
        }

        public Fraction(BigInteger n, BigInteger d)
        {
            this.num = n;
            this.den = d;
            this.cancel();
        }

        public void add(long x)
        {
            this.add(BigInteger.valueOf(x));
        }

        public void add(BigInteger x)
        {
            this.num = this.num.add(this.den.multiply(x));
            this.cancel();
        }

        public void reciproc()
        {
            BigInteger tmpNum = this.num;
            this.num = this.den;
            this.den = tmpNum;
        }

        private void cancel()
        {
            BigInteger gcd = this.gcd(num.abs(), den.abs());
            this.num = this.num.divide(gcd);
            this.den = this.den.divide(gcd);
        }

        private BigInteger gcd(BigInteger a, BigInteger b)
        {
            if (b.equals(BigInteger.ZERO))
            {
                return a;
            }
            if (a.compareTo(b) < 0)
            {
                return (gcd(b, a));
            }
            BigInteger mod = a.mod(b);
            return gcd(b, mod);
        }
    }

}
