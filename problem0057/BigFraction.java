package problem0057;

import java.math.BigInteger;

/**
 *
 * @author micmeyer
 */
public class BigFraction
{

    private BigInteger a, b;

    public BigFraction(BigInteger numerator)
    {
        this(numerator, BigInteger.ONE);
    }

    public BigFraction(BigInteger numerator, BigInteger denominator)
    {
        this.a = numerator;
        this.b = denominator;
        this.toLowestCommonTerms();
    }

    public BigInteger getNumerator()
    {
        return this.a;
    }

    public BigInteger getDenominator()
    {
        return this.b;
    }

    public BigFraction add(BigInteger addend)
    {
        return new BigFraction(this.a.add(addend.multiply(this.b)), this.b);
    }

    public BigFraction add(BigFraction other)
    {
        return new BigFraction(this.a.multiply(other.b).add(other.a.multiply(this.b)), this.b.multiply(other.b));
    }

    public BigFraction subtract(BigInteger sub)
    {
        return new BigFraction(this.a.subtract(sub.multiply(this.b)), this.b);
    }

    public BigFraction subtract(BigFraction other)
    {
        return new BigFraction(this.a.multiply(other.b).subtract(other.a.multiply(this.b)), this.b.multiply(other.b));
    }

    public BigFraction multiply(BigFraction other)
    {
        return new BigFraction(this.a.multiply(other.a), this.b.multiply(other.b));
    }

    public BigFraction divide(BigFraction other)
    {
        return new BigFraction(this.a.multiply(other.b), this.b.multiply(other.a));
    }

    public BigFraction reciproc()
    {
        return new BigFraction(this.b, this.a);
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == null)
        {
            return false;
        }
        if (this.hashCode() != other.hashCode())
        {
            return false;
        }
        if (other instanceof BigFraction)
        {
            BigFraction o = (BigFraction) other;
            return ((this.a == o.a) && (this.b == o.b));
        }
        return false;
    }

    @Override
    public String toString()
    {
        return this.a + "/" + this.b;
    }

    private void toLowestCommonTerms()
    {
        BigInteger gcd = gcd(this.a.abs(), this.b.abs());
        gcd = gcd.multiply(BigInteger.valueOf(this.b.signum()));

        this.a = this.a.divide(gcd);
        this.b = this.b.divide(gcd);
    }

    public static void main(String[] args)
    {
        System.out.println(new BigFraction(BigInteger.valueOf(6), BigInteger.valueOf(10)));
        System.out.println(new BigFraction(BigInteger.valueOf(-6), BigInteger.valueOf(10)));
        System.out.println(new BigFraction(BigInteger.valueOf(6), BigInteger.valueOf(-10)));
        System.out.println(new BigFraction(BigInteger.valueOf(-6), BigInteger.valueOf(-10)));
    }

    public static BigInteger gcd(BigInteger a, BigInteger b)
    {
        return (b.equals(BigInteger.ZERO)) ? a : gcd(b, a.mod(b));
    }

}
