package de.michel.projecteuler.problems0001_0050.problem0032;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * We shall say that an n-digit number is pandigital if it makes use of all the
 * digits 1 to n exactly once; for example, the 5-digit number, 15234, is 1
 * through 5 pandigital.<br/>
 * <br/>
 * The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing
 * multiplicand, multiplier, and product is 1 through 9 pandigital.<br/>
 * <br/>
 * Find the sum of all products whose multiplicand/multiplier/product identity
 * can be written as a 1 through 9 pandigital.<br/>
 * <br/>
 * HINT: Some products can be obtained in more than one way so be sure to only
 * include it once in your sum.
 *
 * @author micmeyer
 */
public class Problem0032
{

    private static final Set<Integer> allProducts = new HashSet<>();

    private static final Map<Integer, int[]> combinations = new HashMap<>();

    private static final int[] digits =
    {
        1, 2, 3, 4, 5, 6, 7, 8, 9
    };

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();
        
        int[] theDigits = new int[digits.length];
        System.arraycopy(digits, 0, theDigits, 0, digits.length);
        generateCombinations(theDigits, 0, new int[5]);
//        
        checkProducts1DigitTimes4Digits();
        checkProducts2DigitTimes3Digits();
        
        System.out.println(allProducts.stream().mapToInt(Integer::intValue).sum());
        
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }
    
    private static void checkProducts1DigitTimes4Digits()
    {
        int factor1, factor2, product;
        for (int number: combinations.keySet())
        {
            factor1 = number / 10000;
            factor2 = number % 10000;
            
            product = factor1 * factor2;
            
            if (digitsMatchTheList(product, combinations.get(number)))
            {
                allProducts.add(product);
//                System.out.printf("%3d * %3d = %5d\n", factor1, factor2, product);
            }
        }
    }
    
    private static void checkProducts2DigitTimes3Digits()
    {
        int factor1, factor2, product;
        for (int number: combinations.keySet())
        {
            factor1 = number / 1000;
            factor2 = number % 1000;
            
            product = factor1 * factor2;
            
            if (digitsMatchTheList(product, combinations.get(number)))
            {
                allProducts.add(product);
//                System.out.printf("%3d * %3d = %5d\n", factor1, factor2, product);
            }
        }
    }

    private static boolean digitsMatchTheList(int number, int[] list)
    {
        int len = list.length;
        int min = (int)power(10, len - 1);
        int max = 10*min;
        
        if (number < min || number >= max)
        {
            return false;
        }
        
        int[] numberAsArray = intToArray(number);
        
        for (int digit: list)
        {
            if (!arrayContains(numberAsArray, digit))
            {
                return false;
            }
        }
        
        return true;
    }
    
    private static void generateCombinations(int[] theDigits, int index, int[] number)
    {
        if (index == 5)
        {
            addNumberToCombinations(number);
        }
        else
        {
            int digit;
            for (int t = 0; t < theDigits.length; t++)
            {
                digit = theDigits[t];
                if (digit >= 0)
                {
                    number[index] = digit;
                    theDigits[t] = -1;
                    generateCombinations(theDigits, index + 1, number);
                    theDigits[t] = digit;
                }
            }
        }
    }

    private static void addNumberToCombinations(int[] number)
    {
        long n = arrayToNumber(number);
        int[] nonOccurring = getNonOccurringDigits(number);
        combinations.put((int) n, nonOccurring);
    }

    private static int[] getNonOccurringDigits(int[] number)
    {
        int[] nonOcc = new int[digits.length - number.length];
        
        int index = 0;
        for (int digit : digits)
        {
            if (!arrayContains(number, digit))
            {
                nonOcc[index] = digit;
                index++;
            }
        }
        return nonOcc;
    }

    private static boolean arrayContains(int[] a, int elem)
    {
        for (int e : a)
        {
            if (e == elem)
            {
                return true;
            }
        }
        return false;
    }

    private static long arrayToNumber(int[] number)
    {
        long n = 0;
        int power = number.length - 1;
        for (int d : number)
        {
            n += d * power(10, power);
            power--;
        }
        return n;
    }

    private static long power(long basis, int power)
    {
        long result = 1;
        for (int p = 0; p < power; p++)
        {
            result *= basis;
        }
        return result;
    }
    
    private static int[] intToArray(int number)
    {
        int n = number;
        int len = 0;
        while (n > 0)
        {
            len++;
            n /= 10;
        }
        
        int[] a = new int[len];
        int index = len - 1;
        while (number > 0)
        {
            a[index] = number % 10;
            number /= 10;
            index--;
        }
        return a;
    }
}
