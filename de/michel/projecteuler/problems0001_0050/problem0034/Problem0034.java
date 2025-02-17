package de.michel.projecteuler.problems0001_0050.problem0034;

/**
 *
 * 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
 *
 * Find the sum of all numbers which are equal to the sum of the factorial of
 * their digits.
 *
 * Note: as 1! = 1 and 2! = 2 are not sums they are not included.
 *
 * @author micmeyer
 */
public class Problem0034
{

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();
        
        
        int totalSum = 0;
//        for (int t = 3; t < 2540161; t++)
//        {
//            int sum = Digits.getDigitsAsList(t).stream().map(Problem0034::fac).mapToInt(Integer::intValue).sum();
//            if (sum == t)
//            {
//                totalSum += t;
//            }
//        }
        for (int t = 3; t < 2540161; t++)
        {
            int sum = 0;
            int n = t;
            while (n > 0 && sum <= t)
            {
                int d = n % 10;
                sum += fac(d);
                n /= 10;
            }
            if (n == 0 && sum == t)
            {
                totalSum += sum;
            }
        }
        System.out.println(totalSum);
        
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }
    
    private static int fac(int n)
    {
        return (n == 0) || (n == 1) ? 1 : n*fac(n - 1);
    }

}
