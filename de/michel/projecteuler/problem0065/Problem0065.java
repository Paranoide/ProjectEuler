package de.michel.projecteuler.problem0065;

import java.math.BigInteger;

/**
 *
 *
 *
 * @author micmeyer
 */
public class Problem0065
{

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        int n = 100;
        Fraction f = new Fraction(1, getNthFractionAddend(n));

        n--;
        while (n > 1)
        {
            f.add(getNthFractionAddend(n));
            f.reciproc();
            n--;
        }

        f.add(getNthFractionAddend(n));

        System.out.println("Sum: " + digitSum(f.num));
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static int digitSum(BigInteger n)
    {
        int sum = 0;
        while (n.compareTo(BigInteger.ZERO) > 0)
        {
            sum += n.mod(BigInteger.TEN).intValue();
            n = n.divide(BigInteger.TEN);
        }
        return sum;
    }

    private static int getNthFractionAddend(int n)
    {
        if (n == 1)
        {
            return 2;
        }
        if (n % 3 == 0)
        {
            return 2 * n / 3;
        }
        else
        {
            return 1;
        }
    }

    private static class Fraction
    {

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
