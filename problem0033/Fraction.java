package problem0033;

/**
 *
 * @author micmeyer
 */
public class Fraction
{

    private int a, b;
    
    public Fraction(int numerator)
    {
        this(numerator, 1);
    }
    
    public Fraction(int numerator, int denominator)
    {
        this.a = numerator;
        this.b = denominator;
        this.toLowestCommonTerms();
    }
    
    public int getNumerator()
    {
        return this.a;
    }
    
    public int getDenominator()
    {
        return this.b;
    }
    
    public Fraction multiply(Fraction other)
    {
        return new Fraction(this.a * other.a, this.b * other.b);
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
        int hash = 7;
        hash = 79 * hash + this.a;
        hash = 79 * hash + this.b;
        return hash;
    }
    
    @Override
    public String toString()
    {
        return this.a + "/" + this.b;
    }
    
    private void toLowestCommonTerms()
    {
        int gcd = gcd(this.a, this.b);
        gcd *= (int)Math.signum(this.b);
        
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
    
    
    private static int gcd(int a, int b)
    {
        return (b == 0) ? Math.abs(a) : Math.abs(gcd(b, a % b));
    }
    
}
