package de.michel.projecteuler.problem0097;

import java.math.BigInteger;

/**
 * @author micmeyer
 */
public class Problem0097
{

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        BigInteger a = BigInteger.valueOf(28433);
        BigInteger p = BigInteger.valueOf(7_830_457);
        BigInteger mod = BigInteger.valueOf(10_000_000_000L);

        BigInteger lastSomeDigitsOfPrime = BigInteger.TWO.modPow(p, mod).multiply(a).add(BigInteger.ONE);

        BigInteger lastTenDigits = lastSomeDigitsOfPrime.mod(mod);

        System.out.println("Result: " + lastTenDigits);

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

}
