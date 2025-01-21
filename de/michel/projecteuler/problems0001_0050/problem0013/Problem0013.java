package de.michel.projecteuler.problems0001_0050.problem0013;

/**
 *
 * @author micmeyer
 */
public class Problem0013
{

    public static void main(String[] args)
    {
        final int RESULT_LENGTH = 55;
        int[] result = new int[RESULT_LENGTH];
        int resultIndex = RESULT_LENGTH-1;

        int carrier = 0;

        int[][] data = Data.DATA;

        int width = data[0].length;
        int height = data.length;
        int digit = width - 1;
        
        for ( ; digit >= 0; digit--)
        {
            int digitSum = 0;
            for (int number = 0; number < height; number++)
            {
                digitSum += data[number][digit];
            }
            digitSum += carrier;
            result[resultIndex] = digitSum % 10;
            resultIndex--;
            carrier = digitSum / 10;
        }
        
        while (carrier > 0)
        {
            result[resultIndex--] = carrier % 10;
            carrier /= 10;
        }
        
        for (int t = resultIndex + 1; t < resultIndex + 11; t++)
        {
            System.out.print(result[t]);
        }
        System.out.println();
        

    }
}
