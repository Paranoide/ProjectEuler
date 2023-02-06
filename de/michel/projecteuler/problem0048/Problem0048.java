package de.michel.projecteuler.problem0048;

import java.math.BigInteger;

/**
 *
 * The series, 1<sup>1</sup> + 2<sup>2</sup> + 3<sup>2</sup> + ... +
 * 10<sup>10</sup> = 10405071317.<br/>
 * <br/>
 * Find the last ten digits of the series, 1<sup>1</sup> + 2<sup>2</sup> +
 * 3<sup>2</sup> + ... + 1000<sup>1000</sup>.
 *
 * @author micmeyer
 */
public class Problem0048
{

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        BigInteger i;
        BigInteger mod = BigInteger.TEN.pow(10);
        long sum = 0;
        for (int n = 1; n <= 1000; n++)
        {
            i = BigInteger.valueOf(n).pow(n);
            i = i.mod(mod);
            sum += i.longValueExact();
        }
        System.out.println("Last ten digits: " + sum % mod.longValueExact());

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

}
