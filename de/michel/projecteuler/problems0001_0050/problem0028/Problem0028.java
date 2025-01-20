package de.michel.projecteuler.problems0001_0050.problem0028;

/**
 *
 * Starting with the number 1 and moving to the right in a clockwise direction a
 * 5 by 5 spiral is formed as follows:
 * <pre>
 * 21 22 23 24 25
 * 20  7  8  9 10
 * 19  6  1  2 11
 * 18  5  4  3 12
 * 17 16 15 14 13
 * </pre>
 * It can be verified that the sum of the numbers on the diagonals is 101.<br/>
 * <br/>
 * What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral
 * formed in the same way?
 *
 * @author Paranoide
 */
public class Problem0028
{

    public static void main(String[] args)
    {

        int n = 1001;
        long sum = 1;
        int number = 3;

        for (int k = 2; k <= n; k += 2)
        {
            sum += 4 * number + 6 * k;
            number += 4 * k + 2;
        }

        System.out.println(sum);
    }
}
