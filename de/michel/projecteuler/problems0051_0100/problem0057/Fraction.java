package de.michel.projecteuler.problems0051_0100.problem0057;

/**
 *
 * @author micmeyer
 */
public class Fraction
{

    private long a, b;
    
    public Fraction(long numerator)
    {
        this(numerator, 1);
    }
    
    public Fraction(long numerator, long denominator)
    {
        this.a = numerator;
        this.b = denominator;
        this.toLowestCommonTerms();
    }
    
    public long getNumerator()
    {
        return this.a;
    }
    
    public long getDenominator()
    {
        return this.b;
    }
    
    public Fraction add(long add)
    {
        return new Fraction(this.a + add*this.b, this.b);
    }
    
    public Fraction add(Fraction other)
    {
        return new Fraction(this.a * other.b + other.a * this.b, this.b * other.b);
    }
    
    public Fraction subtract(long sub)
    {
        return new Fraction(this.a - sub*this.b, this.b);
    }
    
    public Fraction subtract(Fraction other)
    {
        return new Fraction(this.a * other.b - other.a * this.b, this.b * other.b);
    }
    
    public Fraction multiply(Fraction other)
    {
        return new Fraction(this.a * other.a, this.b * other.b);
    }
    
    public Fraction divide(Fraction other)
    {
        return new Fraction(this.a * other.b, this.b * other.a);
    }
    
    public Fraction reciproc()
    {
        return new Fraction(this.b, this.a);
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
        if (other instanceof Fraction)
        {
            Fraction o = (Fraction)other;
            return ((this.a == o.a) && (this.b == o.b));
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 97 * hash + (int) (this.a ^ (this.a >>> 32));
        hash = 97 * hash + (int) (this.b ^ (this.b >>> 32));
        return hash;
    }
    
    @Override
    public String toString()
    {
        return this.a + "/" + this.b;
    }
    
    private void toLowestCommonTerms()
    {
        long gcd = gcd(this.a, this.b);
        gcd *= (long)Math.signum(this.b);
        
        this.a = this.a / gcd;
        this.b = this.b / gcd;
    }
    
    public static void main(String[] args)
    {
        System.out.println(new Fraction(6, 10));
        System.out.println(new Fraction(-6, 10));
        System.out.println(new Fraction(6, -10));
        System.out.println(new Fraction(-6, -10));
    }
    
    
    private static long gcd(long a, long b)
    {
        return (b == 0) ? Math.abs(a) : Math.abs(gcd(b, a % b));
    }
    
}
