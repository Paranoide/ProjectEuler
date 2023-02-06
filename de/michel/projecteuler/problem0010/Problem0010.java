package de.michel.projecteuler.problem0010;

import java.util.List;
import de.michel.projecteuler.util.PrimeGenerator;

/**
 *
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.<br/>
 * Find the sum of all the primes below two million.
 *
 * @author micmeyer
 */
public class Problem0010
{

    public static void main(String[] args)
    {
        PrimeGenerator pg = new PrimeGenerator();
        List<Long> list = pg.generatePrimesSmallerThanN(2000000);
        System.out.println(list.get(0));
        System.out.println(list.get(list.size() - 1));
        System.out.println(list.stream().mapToLong(Long::longValue).sum());
    }
}
