package de.michel.projecteuler.problems0001_0050.problem0017;

/**
 *
 * 
 * 
 * @author micmeyer
 */
public class Problem0017
{
    public static void main(String[] args)
    {
        long lettersum = 0;
        for (int t = 1; t <= 1000; t++)
        {
            String s = NumbersToWords.numberToWords(t);
            s = s.replaceAll("\\W", "");
//            System.out.println(s);
            lettersum += s.length();
        }
        
        System.out.println(lettersum);
    }
}
