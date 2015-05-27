package problem0003;

/**
 *
 * The prime factors of 13195 are 5, 7, 13 and 29.<br />
 * What is the largest prime factor of the number 600851475143 ?
 * 
 * @author micmeyer
 */
public class Problem0003
{
    public static void main(String[] args)
    {
        final long TARGET = 600851475143L;
        final long MAX_PRIME = (long)Math.sqrt(TARGET);
        
        long curr = TARGET;
        int prime = 3;
        
        long result = 1;
        
        while (curr > 1 && prime <= MAX_PRIME)
        {
            if (curr % prime == 0)
            {
//                System.out.println(prime);
                curr /= prime;
                result = prime;
            }
            else
            {
                prime += 2;
            }
        }
        
//        if (curr > 1)
//        {
//            System.out.println(curr);
//        }
//        
//        System.out.println();
        System.out.println("Largest prime is: " + (curr > 1 ? curr : prime));

    }
}