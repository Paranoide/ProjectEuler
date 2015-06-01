package problem0031;

/**
 *
 * In England the currency is made up of pound, £, and pence, p, and there are
 * eight coins in general circulation:<br/>
 * <br/>
 * 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).<br/>
 * It is possible to make £2 in the following way:<br/>
 * <br/>
 * 1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p<br/>
 * How many different ways can £2 be made using any number of coins?
 *
 * @author micmeyer
 */
public class Problem0031
{
    
    private static int[] coins = {200, 100, 50, 20, 10, 5, 2, 1};
    
    private static int possCount = 0;
    
    public static void main(String[] args)
    {
        possibilities(0, 0);
        
        System.out.println(possCount);
    }
    
    private static void possibilities(int index, int sum)
    {
        if (sum == 200)
        {
            possCount++;
            return;
        }
        if (sum > 200)
        {
            return;
        }
        
        while (index < coins.length)
        {
            sum += coins[index];
            possibilities(index, sum);
            sum -= coins[index];
            index++;
        }
    }
}
