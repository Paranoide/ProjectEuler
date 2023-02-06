package de.michel.projecteuler.problem0009;

/**
 *
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for
 * which, <pre>a^2 + b^2 = c^2</pre> For example,
 * <code>3^2 + 4^2 = 9 + 16 = 25 = 5^2.</code><br/>
 * There exists exactly one Pythagorean triplet for which
 * <code>a + b + c = 1000.</code><br/>
 * Find the product <code>abc</code>.
 *
 * @author micmeyer
 */
public class Problem0009
{

    public static void main(String[] args)
    {
        for (int a = 1; a < 334; a++)
        {
            for (int b = a; b < (1000-a)/2; b++)
            {
                int c = 1000 - (a+b);
                
//                System.out.printf("(a,b,c) = (%d, %d, %d)  %d + %d = %d == %d\n",a,b,c, a*a, b*b, a*a + b*b, c*c);
                
                if ( (a*a + b*b) == c*c )
                {
                    System.out.printf("a + b + c = %d + %d + %d = %d\n", a, b, c, a+b+c);
                    System.out.printf("a * b * c = %d * %d * %d = %d\n", a, b, c, a*b*c);
                }
            }
        }
    }

}
