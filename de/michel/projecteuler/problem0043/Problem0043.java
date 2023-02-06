package de.michel.projecteuler.problem0043;

import java.util.Arrays;
import de.michel.projecteuler.util.Digits;

/**
 *
 * The number, 1406357289, is a 0 to 9 pandigital number because it is made up
 * of each of the digits 0 to 9 in some order, but it also has a rather
 * interesting sub-string divisibility property.<br/>
 * <br/>
 * Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note
 * the following:
 * <pre>
 * d2d3d4=406 is divisible by 2 (rule 1)
 * d3d4d5=063 is divisible by 3 (rule 2)
 * d4d5d6=635 is divisible by 5 (rule 3)
 * d5d6d7=357 is divisible by 7 (rule 4)
 * d6d7d8=572 is divisible by 11 (rule 5)
 * d7d8d9=728 is divisible by 13 (rule 6)
 * d8d9d10=289 is divisible by 17 (rule 7)
 * </pre>
 * Find the sum of all 0 to 9 pandigital numbers with this property.
 *
 * @author micmeyer
 */
public class Problem0043
{

    private static long sum = 0;

    private static final int[] NUMBER = new int[10];

    private static final boolean[] DIGITS = new boolean[10];

    static
    {
        Arrays.fill(DIGITS, true);
    }

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        findSolution(7);

        System.out.println("Sum: " + sum);

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static void findSolution(int rule)
    {
        switch (rule)
        {
            case 0:
                for (int t = 0; t < DIGITS.length; t++)
                {
                    if (DIGITS[t])
                    {
                        NUMBER[0] = t;
                    }
                }
                sum += Digits.arrayToLong(NUMBER);
                break;
            case 1:
                for (int t = 0; t < 10; t++)
                {
                    if (DIGITS[t])
                    {
                        DIGITS[t] = false;

                        NUMBER[1] = t;
                        findSolution(rule - 1);

                        DIGITS[t] = true;
                    }
                }
                break;
            case 2:
                int mod = (NUMBER[3] + NUMBER[4]) % 3;
                int start = (3 - mod) % 3;
                for (int t = start; t < 10; t += 3)
                {
                    if (DIGITS[t])
                    {
                        DIGITS[t] = false;

                        NUMBER[2] = t;
                        findSolution(rule - 1);

                        DIGITS[t] = true;
                    }
                }
                break;
            case 3:
                for (int t = 0; t < 10; t += 2)
                {
                    if (DIGITS[t])
                    {
                        DIGITS[t] = false;

                        NUMBER[3] = t;
                        findSolution(rule - 1);

                        DIGITS[t] = true;
                    }
                }
                break;
            case 4:
                int lastTwo = NUMBER[5] * 10 + NUMBER[6];
                for (int t = 0; t < 10; t++)
                {
                    if (DIGITS[t])
                    {
                        int allThree = t * 100 + lastTwo;
                        if (allThree % 7 == 0)
                        {
                            DIGITS[t] = false;

                            NUMBER[4] = t;
                            findSolution(rule - 1);

                            DIGITS[t] = true;
                        }
                    }
                }
                break;
            case 5:
                lastTwo = NUMBER[6] * 10 + NUMBER[7];
                if ((500 + lastTwo) % 11 == 0)
                {
                    if (DIGITS[5])
                    {
                        DIGITS[5] = false;

                        NUMBER[5] = 5;
                        findSolution(rule - 1);

                        DIGITS[5] = true;
                    }
                }
                break;
            case 6:
                lastTwo = NUMBER[7] * 10 + NUMBER[8];
                boolean found = false;
                for (int t = 0; t < 10 && !found; t++)
                {
                    if (DIGITS[t])
                    {
                        int allThree = 100 * t + lastTwo;

                        if (allThree % 13 == 0)
                        {
                            found = true;

                            DIGITS[t] = false;

                            NUMBER[6] = t;

                            findSolution(rule - 1);

                            DIGITS[t] = true;
                        }
                    }
                }
                break;
            case 7:
                for (int t = 1; t < 59; t++)
                {
                    int lastThree = t * 17;
                    insertIntoNumber(lastThree, 7, 3);
                    if (removeFromDigits(lastThree, 3))
                    {
                        findSolution(rule - 1);
                        addToDigits(lastThree, 3);
                    }
                }
                break;
            default:
                findSolution(rule - 1);
        }
    }

    private static boolean removeFromDigits(int n, int digitCount)
    {
        boolean possible = true;
        int nTmp = n;
        int digitCountTmp = digitCount;
        while (digitCountTmp > 0 && possible)
        {
            int digit = nTmp % 10;
            if (DIGITS[digit])
            {
                DIGITS[digit] = false;
            }
            else
            {
                possible = false;
            }
            nTmp /= 10;
            digitCountTmp--;
        }

        if (!possible)
        {
            addToDigits(n, digitCount - digitCountTmp - 1);
        }

        return possible;
    }

    private static void addToDigits(int n, int digitCount)
    {
        while (digitCount > 0)
        {
            DIGITS[n % 10] = true;
            n /= 10;
            digitCount--;
        }
    }

    private static void insertIntoNumber(int n, int index, int digitCount)
    {
        while (digitCount > 0)
        {
            NUMBER[index + digitCount - 1] = n % 10;
            n /= 10;
            digitCount--;
        }
    }

}
