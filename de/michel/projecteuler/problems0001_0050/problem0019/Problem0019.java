package de.michel.projecteuler.problems0001_0050.problem0019;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are given the following information, but you may prefer to do some
 * research for yourself.
 * <pre>
 * - 1 Jan 1900 was a Monday.
 * - Thirty days has September,
 *   April, June and November.
 *   All the rest have thirty-one,
 *   Saving February alone,
 *   Which has twenty-eight, rain or shine.
 *   And on leap years, twenty-nine.
 * - A leap year occurs on any year evenly divisible by 4, but not on a century
 * unless it is divisible by 400.
 * </pre> How many Sundays fell on the first of the month during th twentieth
 * century (1 Jan 1901 to 31 Dec 2000)?
 *
 * @author micmeyer
 */
public class Problem0019
{

    private static final Map<Integer, Integer> monthsMap = new HashMap<>();

    static
    {
        monthsMap.put(1, 31);
        monthsMap.put(2, 28);
        monthsMap.put(3, 31);
        monthsMap.put(4, 30);
        monthsMap.put(5, 31);
        monthsMap.put(6, 30);
        monthsMap.put(7, 31);
        monthsMap.put(8, 31);
        monthsMap.put(9, 30);
        monthsMap.put(10, 31);
        monthsMap.put(11, 30);
        monthsMap.put(12, 31);
    }

    public static void main(String[] args)
    {
        int day = 1;

        boolean leapYear = leapYear(1900);
        for (int month = 1; month <= 12; month++)
        {
            int daysToSkip = monthsMap.get(month);

            if (month == 2 && leapYear)
            {
                daysToSkip++;
            }

            day += daysToSkip;
            day = day % 7;
        }

        System.out.println("Day on Jan 1st 1901: " + day);

        int sundaysOnFirstOfMonth = 0;
        if (day == 0)
        {
            sundaysOnFirstOfMonth++;
        }

        for (int year = 1901; year <= 2000; year++)
        {
            leapYear = leapYear(year);
            for (int month = 1; month <= 12; month++)
            {
                int daysToSkip = monthsMap.get(month);

                if (month == 2 && leapYear)
                {
                    daysToSkip++;
                }

                day += daysToSkip;
                day = day % 7;

                if (day == 0)
                {
                    sundaysOnFirstOfMonth++;
                }
            }
        }

        System.out.println("Sundays on first of the month: " + sundaysOnFirstOfMonth);

    }

    private static boolean leapYear(int year)
    {
        return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
    }
}
